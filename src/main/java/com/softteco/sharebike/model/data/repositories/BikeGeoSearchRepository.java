package com.softteco.sharebike.model.data.repositories;

import com.softteco.sharebike.model.data.Bike;
import com.softteco.sharebike.model.data.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BikeGeoSearchRepository extends CrudRepository<Bike, Long> {

    @Query("select b from Bike b  " +
            "where 6371000 * function('radians',abs(:longitude-b.longitude))  * function('cos', function('radians',:latitude)) <= :distance " +
            "and 6371000 * function('radians', abs(:latitude - b.latitude))  <= :distance")
    List<Bike> findNearBikes(@Param("latitude") double latitude,
                             @Param("longitude") double longitude,
                             @Param("distance") double distance);

    @Query("select b from Bike b  " +
            "where 6371000 * function('radians', abs(:longitude-b.longitude)) * function('cos', function('radians',:latitude)) <= :distance " +
            "and 6371000 * function('radians', abs(:latitude - b.latitude))  <= :distance " +
            "and b.status = :status")
    List<Bike> findNearBikesByStatus(@Param("latitude") double latitude,
                                     @Param("longitude") double longitude,
                                     @Param("distance") double distance,
                                     @Param("status") Status status);

    List<Bike> findByStatus(Status status);

}
