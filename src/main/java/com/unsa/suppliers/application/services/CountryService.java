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
import java.util.Optional;

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
    public CountryEntity createCountry(CountryEntity countryEntity) throws CountryDuplicatedException {
        Optional<CountryEntity> optionalCountry = countryRepository.findByName(countryEntity.getName());
        if (optionalCountry.isPresent()) { throw new CountryDuplicatedException(); }
        return countryRepository.save(countryEntity);
    }
    @Transactional
    public void updateCountry(Integer id, CountryEntity countryEntity) throws CountryNotFoundException, CountryDuplicatedException {
        CountryEntity optionalCountry = countryRepository.findById(id).orElseThrow(CountryNotFoundException::new);
        if (!optionalCountry.getName().equals(countryEntity.getName())) {
            Optional<CountryEntity> optionalCountryName = countryRepository.findByName(countryEntity.getName());
            if (optionalCountryName.isPresent()) { throw new CountryDuplicatedException(); }
        }
        countryEntity.setId(id);
        countryRepository.save(countryEntity);
    }
    @Transactional
    public void changeCountryState(Integer id, String state) throws CountryNotFoundException, StateNotFoundException {
        CountryEntity countryEntity = countryRepository.findById(id).orElseThrow(CountryNotFoundException::new);
        StateEntity stateEntity = stateRepository.findByName(state).orElseThrow(StateNotFoundException::new);
        countryEntity.setState(stateEntity);
        countryRepository.save(countryEntity);
    }
}
