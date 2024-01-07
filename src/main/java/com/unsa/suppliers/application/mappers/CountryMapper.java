package com.unsa.suppliers.application.mappers;

import com.unsa.suppliers.domain.dtos.countries.CountryRequest;
import com.unsa.suppliers.domain.dtos.countries.CountryResponse;
import com.unsa.suppliers.domain.entities.CountryEntity;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {
    public CountryResponse entityToResponse(CountryEntity countryEntity) {
        return CountryResponse.builder()
                .id(countryEntity.getId())
                .name(countryEntity.getName())
                .state(countryEntity.getState().getName())
                .build();
    }
    public CountryEntity requestToEntity(CountryRequest countryRequest) {
        return CountryEntity.builder()
                .name(countryRequest.getName())
                .build();
    }
}
