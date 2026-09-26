package com.alpha.locationservices.mapper;

import com.alpha.locationservices.entity.AirportEntity;
import com.alpha.payload.request.AirportRequest;
import com.alpha.payload.response.AirportResponse;

public class AirportMapper {
    public static AirportResponse toResponse(AirportEntity airport){
        return AirportResponse.builder()
                .id(airport.getId())
                .iataCode(airport.getIataCode())
                .name(airport.getName())
                .address(airport.getAddress())
                .geoCode(airport.getGeoCode())
                .timeZoneId(airport.getTimeZone())
                .city(CityMapper.toResponse(airport.getCity()))
                .detailedName(airport.getDetailedName())
                .build();
    }

    public static AirportEntity toEntity(AirportRequest request){
        if(request == null) return null;
        return AirportEntity.builder()
                .name(request.getName().trim())
                .iataCode(request.getIataCode().trim())
                .address(request.getAddress())
                .timeZone(request.getTimeZoneId())
                .geoCode(request.getGeoCode())
                .build();
    }

    public static AirportEntity updateEntity(AirportRequest request, AirportEntity existingAirport){
        if(request == null || existingAirport == null) return null;
        if(request.getIataCode() != null){
            existingAirport.setIataCode(request.getIataCode());
        }
        if(request.getName() != null){
            existingAirport.setName(request.getName());
        }
        if(request.getTimeZoneId() != null){
            existingAirport.setTimeZone(request.getTimeZoneId());
        }
        if(request.getAddress() != null){
            existingAirport.setAddress(request.getAddress());
        }
        if(request.getGeoCode() != null){
            existingAirport.setGeoCode(request.getGeoCode());
        }
        return existingAirport;
    }
}

