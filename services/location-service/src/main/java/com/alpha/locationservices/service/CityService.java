package com.alpha.locationservices.service;

import com.alpha.locationservices.entity.CityEntity;
import com.alpha.locationservices.exceptions.CityAlreadyExistsException;
import com.alpha.locationservices.exceptions.CityNotFoundException;
import com.alpha.locationservices.mapper.CityMapper;
import com.alpha.locationservices.repository.CityRepository;
import com.alpha.payload.request.CityRequest;
import com.alpha.payload.response.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    final private CityRepository cityRepository;

    public CityResponse createCity(CityRequest cityRequest){
        if(cityRepository.existsByCityCode(cityRequest.getCityCode())){
            throw new CityAlreadyExistsException("City with given code " +  cityRequest.getCityCode() + " already exists");
        }
        CityEntity newCity = cityRepository.save(CityMapper.toEntity(cityRequest));
        return CityMapper.toResponse(newCity);
    }

    public CityResponse getCityById(Long id){
        CityEntity city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not exists with id " + id));
        return CityMapper.toResponse(city);
    }

    public CityResponse updateCity(CityRequest cityRequest, Long id){
        CityEntity existingCity = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not exists with id " + id));
        if(cityRepository.existsByCityCodeAndIdNot(cityRequest.getCityCode(), id)){
            throw new CityAlreadyExistsException("City with given code " +  cityRequest.getCityCode() + " already exists");
        }
        CityMapper.updateEntity(existingCity, cityRequest);
        CityEntity updatedCity = cityRepository.save(existingCity);
        return CityMapper.toResponse(updatedCity);

    }

    public void deleteCity(Long id){
        CityEntity city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not exists with id " + id));
        cityRepository.delete(city);
    }

    public Page<CityResponse> getAllCities(Pageable pageable){
        return cityRepository.findAll(pageable).map(CityMapper::toResponse);
    }

    public Page<CityResponse> searchCities(String keyword, Pageable pageable ){
        return cityRepository.searchByKeyword(keyword, pageable).map(CityMapper::toResponse);
    }

    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable){
        return cityRepository.findByCountryCodeIgnoreCase(countryCode, pageable).map(CityMapper::toResponse);
    }

    public boolean cityExists(String cityCode){
        return cityRepository.existsByCityCode(cityCode);
    }


}
