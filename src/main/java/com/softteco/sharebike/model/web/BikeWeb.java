package com.softteco.sharebike.model.web;

import com.softteco.sharebike.model.data.Bike;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class BikeWeb {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String status;
    @Getter
    @Setter
    private GeoPosition position;

    public boolean isNear(GeoPosition position, double radius) {
        if (position == null)
            return false;
        return (this.position.getLatitude() - position.getLatitude()) * (this.position.getLatitude() - position.getLatitude())
                + (this.position.getLongitude() - position.getLongitude()) * (this.position.getLongitude() - position.getLongitude())
                <= radius * radius;
    }

    public BikeWeb (Bike bike) {
        this.id = bike.getBikeId();
        this.position = new GeoPosition(bike.getLatitude(), bike.getLongitude());
        this.status = bike.getStatus().getStatus();
    }
}
