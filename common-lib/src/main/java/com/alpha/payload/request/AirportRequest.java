package com.alpha.payload.request;

import com.alpha.embaddable.Address;
import com.alpha.embaddable.GeoCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirportRequest {

    @NotBlank(message = "IATA Code is required")
    @Size(min = 3, max = 3, message = "IATA code must be 3 characters")
    private String iataCode;

    @NotBlank(message = "airport name is required")
    private String name;

    @NotBlank()
    private String timeZoneId;

    @NotNull(message = "City id is required")
    private Long cityId;

    @Valid
    private Address address;

    @Valid
    private GeoCode geoCode;


}
