package com.alpha.locationservices.mapper;

import com.alpha.locationservices.entity.CityEntity;
import com.alpha.payload.request.CityRequest;
import com.alpha.payload.response.CityResponse;

public class CityMapper {
    public static CityEntity toEntity(CityRequest request){
        if(request == null) return null;
        return CityEntity.builder()
                .cityName(request.getCityName().trim())
                .cityCode(request.getCityCode().trim())
                .countryName(request.getCountryName().trim())
                .countryCode(request.getCountryCode().trim())
                .regionCode(request.getRegionCode().trim())
                .timeZoneId(request.getTimeZoneOffSet().trim())
                .build();
    }

    public static CityResponse toResponse(CityEntity city){
        if(city == null) return null;
        return CityResponse.builder()
                .id(city.getId())
                .cityName(city.getCityName())
                .cityCode(city.getCityCode())
                .countryName(city.getCountryCode())
                .countryCode(city.getCountryCode())
                .regionCode(city.getRegionCode())
                .timeZoneOffSet(city.getTimeZoneId())
                .build();
    }

    public static CityEntity updateEntity(CityEntity city, CityRequest request){
        if(city == null || request == null) return null;
        if(request.getCityName() != null){
            city.setCityName(request.getCityName().trim());
        }
        if(request.getCityCode() != null){
            city.setCityCode(request.getCityCode().trim());
        }
        if(request.getCountryCode() != null){
            city.setCountryCode(request.getCountryCode().trim());
        }
        if(request.getCountryName() != null){
            city.setCountryName(request.getCountryName().trim());
        }
        if(request.getRegionCode() != null){
            city.setRegionCode(request.getRegionCode().trim());
        }
        if(request.getTimeZoneOffSet() != null){
            city.setTimeZoneId(request.getTimeZoneOffSet().trim());
        }
        return city;
    }
}
