package com.alpha.payload.response;

import com.alpha.embaddable.Address;
import com.alpha.embaddable.GeoCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AirportResponse {
    private Long id;
    private String iataCode;
    private String name;
    private Address address;
    private GeoCode geoCode;
    private String timeZoneId;
    private CityResponse city;
    private String detailedName;
}
