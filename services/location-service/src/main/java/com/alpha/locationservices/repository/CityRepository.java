package com.alpha.locationservices.repository;

import com.alpha.locationservices.entity.CityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
    boolean existsByCityCode(String cityCode);
    boolean existsByCityCodeAndIdNot(String cityCode, Long Id);

    Page<CityEntity> findByCountryCodeIgnoreCase(String countryCode, Pageable pageable);

    @Query("""
        select c from CityEntity c
        where lower(c.cityName) like lower(concat('%', :keyWord, '%'))
           or lower(c.cityCode) like lower(concat('%', :keyWord, '%'))
           or lower(c.countryCode) like lower(concat('%', :keyWord, '%'))
           or lower(c.countryName) like lower(concat('%', :keyWord, '%'))
           or lower(c.regionCode) like lower(concat('%', :keyWord, '%'))
    """)
    Page<CityEntity> searchByKeyword(String keyWord, Pageable pageable);

}
