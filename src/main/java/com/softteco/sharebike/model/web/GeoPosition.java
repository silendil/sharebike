package com.softteco.sharebike.model.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class GeoPosition {
    @Getter
    @Setter
    private double latitude;
    @Getter
    @Setter
    private double longitude;
}
