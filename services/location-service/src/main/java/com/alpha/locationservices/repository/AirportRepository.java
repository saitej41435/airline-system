package com.alpha.locationservices.repository;

import com.alpha.locationservices.entity.AirportEntity;
import com.alpha.payload.response.AirportResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<AirportEntity, Long> {
    boolean existsByIataCode(String iataCode);
    boolean existsByIataCodeAndIdNot(String iataCode, Long id);

    public AirportEntity findByIataCode(String iataCode);
    public List<AirportEntity> findByCityId(Long cityId);

}
