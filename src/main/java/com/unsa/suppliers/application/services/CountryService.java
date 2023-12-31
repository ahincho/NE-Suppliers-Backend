package com.unsa.suppliers.application.services;

import com.unsa.suppliers.domain.entities.CountryEntity;
import com.unsa.suppliers.domain.entities.StateEntity;
import com.unsa.suppliers.domain.exceptions.countries.*;
import com.unsa.suppliers.domain.exceptions.states.StateNotFoundException;
import com.unsa.suppliers.domain.repositories.CountryRepository;
import com.unsa.suppliers.domain.repositories.StateRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

import static com.unsa.suppliers.application.services.StateService.ACTIVE_STATE;

@Service
public class CountryService {
    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;
    public CountryService(CountryRepository countryRepository, StateRepository stateRepository) {
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
    }
    public List<CountryEntity> getAllCountries() {
        return countryRepository.findAll();
    }
    public CountryEntity getCountryById(Integer id) throws CountryNotFoundException {
        return countryRepository.findById(id).orElseThrow(CountryNotFoundException::new);
    }
    @Transactional
    public CountryEntity createCountry(CountryEntity countryEntity) throws CountryDuplicatedException, CountryNotFoundException {
        if (countryRepository.existsByName(countryEntity.getName())) { throw new CountryDuplicatedException(); }
        StateEntity stateEntity = stateRepository.findByName(ACTIVE_STATE).orElseThrow(CountryNotFoundException::new);
        countryEntity.setState(stateEntity);
        return countryRepository.save(countryEntity);
    }
    @Transactional
    public void updateCountry(Integer id, CountryEntity countryEntity) throws CountryNotFoundException, CountryDuplicatedException {
        CountryEntity existingCountry = countryRepository.findById(id).orElseThrow(CountryNotFoundException::new);
        if (!existingCountry.getName().equals(countryEntity.getName()) && countryRepository.existsByName(countryEntity.getName())) {
            { throw new CountryDuplicatedException(); }
        }
        existingCountry.setName(countryEntity.getName());
        countryRepository.save(existingCountry);
    }
    @Transactional
    public void changeCountryState(Integer id, String state) throws CountryNotFoundException, StateNotFoundException {
        CountryEntity countryEntity = countryRepository.findById(id).orElseThrow(CountryNotFoundException::new);
        StateEntity stateEntity = stateRepository.findByName(state).orElseThrow(StateNotFoundException::new);
        countryEntity.setState(stateEntity);
        countryRepository.save(countryEntity);
    }
}
