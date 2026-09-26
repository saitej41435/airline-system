package com.alpha.locationservices.controller;

import com.alpha.locationservices.service.CityService;
import com.alpha.payload.request.CityRequest;
import com.alpha.payload.response.ApiResponse;
import com.alpha.payload.response.CityResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.SortDirection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor

public class CityController {
    private final CityService cityService;

    @PostMapping()
    public ResponseEntity<CityResponse> createCity(@Valid @RequestBody CityRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(cityService.createCity(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getCityById(@PathVariable Long id){
        return ResponseEntity.ok(cityService.getCityById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CityResponse>> getAllCities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "cityName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(cityService.getAllCities(pageable));

    }

    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> updateCity(@Valid @RequestBody CityRequest request, @PathVariable Long id){
        return ResponseEntity.ok(cityService.updateCity(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCity(@PathVariable Long id){
        cityService.deleteCity(id);
        return ResponseEntity.ok(new ApiResponse("City deleted successfully"));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<CityResponse>> searchCities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam String keyword
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(cityService.searchCities(keyword, pageable));
    }

    @GetMapping("/country/{countryCode}")
    public ResponseEntity<Page<CityResponse>> getCitiesByCountryCode(
            @PathVariable String countryCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(cityService.getCitiesByCountryCode(countryCode, pageable));
    }

    @GetMapping("/exists/{cityCode}")
    public ResponseEntity<Boolean> checkCityExists(@PathVariable String cityCode){
        return ResponseEntity.ok(cityService.cityExists(cityCode.toUpperCase()));
    }

}
