package com.alpha.locationservices.controller;

import com.alpha.locationservices.service.AirportService;
import com.alpha.payload.request.AirportRequest;
import com.alpha.payload.response.AirportResponse;
import com.alpha.payload.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;

    @PostMapping()
    public ResponseEntity<AirportResponse> createAirport(@Valid @RequestBody AirportRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(airportService.createAirport(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponse> getAirportById(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok(airportService.getAirportById(id));
    }

    @GetMapping()
    public ResponseEntity<List<AirportResponse>> getAllAirports(){
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> updateAirport(@Valid @RequestBody AirportRequest request, @PathVariable Long id){
        return ResponseEntity.ok(airportService.updateAirport(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAirport(@PathVariable Long id){
        airportService.deleteAirport(id);
        return ResponseEntity.ok(new ApiResponse("Airport deleted successfully"));
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<AirportResponse>> getAirportByCityId(@PathVariable Long cityId){
        return ResponseEntity.ok(airportService.getAirportByCityId(cityId));
    }
}
