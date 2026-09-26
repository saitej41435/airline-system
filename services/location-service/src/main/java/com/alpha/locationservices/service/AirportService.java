package com.alpha.locationservices.service;

import com.alpha.locationservices.entity.AirportEntity;
import com.alpha.locationservices.entity.CityEntity;
import com.alpha.locationservices.exceptions.AirportNotFoundException;
import com.alpha.locationservices.exceptions.CityNotFoundException;
import com.alpha.locationservices.exceptions.IataCodeAlreadyExistsException;
import com.alpha.locationservices.mapper.AirportMapper;
import com.alpha.locationservices.repository.AirportRepository;
import com.alpha.locationservices.repository.CityRepository;
import com.alpha.payload.request.AirportRequest;
import com.alpha.payload.response.AirportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    public AirportResponse createAirport(AirportRequest request){
        if(airportRepository.existsByIataCode(request.getIataCode())){
            throw new IataCodeAlreadyExistsException("IataCode" + request.getIataCode() + "already exists");
        }

        CityEntity city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new CityNotFoundException("City not exists with id " + request.getCityId()));

        AirportEntity airport = AirportMapper.toEntity(request);
        airport.setCity(city);

        return AirportMapper.toResponse(airportRepository.save(airport));
    }
    public AirportResponse getAirportById(Long id){
        AirportEntity airport = airportRepository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException("Airport not found with id " + id));
        return AirportMapper.toResponse(airport);
    }

    public List<AirportResponse> getAllAirports(){
        return airportRepository.findAll().stream()
                .map(AirportMapper::toResponse)
                .collect(Collectors.toList());
    }

    public AirportResponse updateAirport(Long id, AirportRequest request){
        AirportEntity existingAirport = airportRepository.findById(id)
                .orElseThrow(() ->new AirportNotFoundException("Airport not found with id " + id));
        if(airportRepository.existsByIataCodeAndIdNot(request.getIataCode(), id)){
            throw new IataCodeAlreadyExistsException("IataCode" + request.getIataCode() + "already exists");
        }

        AirportMapper.updateEntity(request, existingAirport);
        AirportEntity updatedAirport = airportRepository.save(existingAirport);
        return AirportMapper.toResponse(updatedAirport);

    }
    public void deleteAirport(Long id){
        AirportEntity airport = airportRepository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException("Airport not found with id " + id));
        airportRepository.delete(airport);
    }

    public List<AirportResponse> getAirportByCityId(Long cityId){
        return airportRepository.findByCityId(cityId)
                .stream().map(AirportMapper::toResponse)
                .collect(Collectors.toList());
    }
}
