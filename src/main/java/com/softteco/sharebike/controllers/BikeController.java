package com.softteco.sharebike.controllers;

import com.softteco.sharebike.model.data.Bike;
import com.softteco.sharebike.model.data.Status;
import com.softteco.sharebike.model.data.repositories.BikeGeoSearchRepository;
import com.softteco.sharebike.model.data.repositories.StatusRepository;
import com.softteco.sharebike.model.web.BikeWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/bikes")
public class BikeController {

    @Autowired
    private BikeGeoSearchRepository bikeGeoSearchRepository;

    @Autowired
    private StatusRepository statusRepository;

    @PostMapping()
    @Transactional
    @ResponseBody
    public BikeWeb addNewBike(@RequestBody BikeWeb bikeWeb) {
        Status status = statusRepository.findStatusByStatus(bikeWeb.getStatus().toUpperCase());
        if (status == null) {
            status = new Status();
            status.setStatus(bikeWeb.getStatus().toUpperCase());
            statusRepository.save(status);
        }
        Bike bike = new Bike();
        bike.setStatus(status);
        bike.setLatitude(bikeWeb.getPosition().getLatitude());
        bike.setLongitude(bikeWeb.getPosition().getLongitude());
        bikeGeoSearchRepository.save(bike);
        return new BikeWeb(bike);
    }

    @GetMapping()
    public List<BikeWeb> getBikes(@RequestParam("status") Optional<String> statusString,
                                  @RequestParam("latitude") Optional<Double> latitude,
                                  @RequestParam("longitude") Optional<Double> longitude,
                                  @RequestParam("distance") Optional<Double> distance) {
        if (statusString.isEmpty()) {
            if (latitude.isPresent() && longitude.isPresent() && distance.isPresent()) {
                return transform(bikeGeoSearchRepository.findNearBikes(latitude.get(), longitude.get(), distance.get()));
            } else
                return StreamSupport.stream(bikeGeoSearchRepository.findAll().spliterator(), false)
                        .map(BikeWeb::new).collect(Collectors.toList());
        }
        Status status = statusRepository.findStatusByStatus(statusString.get().toUpperCase());
        if (status == null) {
            return new ArrayList<>();
        }
        if (latitude.isPresent() && longitude.isPresent() && distance.isPresent()) {
            return transform(bikeGeoSearchRepository.findNearBikesByStatus(latitude.get(), longitude.get(), distance.get(), status));
        }
        return bikeGeoSearchRepository.findByStatus(status).stream().map(BikeWeb::new).collect(Collectors.toList());
    }

    private static List<BikeWeb> transform(List<Bike> bikes) {
        List<BikeWeb> result = new ArrayList<>();
        if (bikes != null && !bikes.isEmpty()) {
            result = bikes.stream().map(BikeWeb::new).collect(Collectors.toList());
        }
        return result;
    }
}
