package com.softteco.sharebike.model.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bikes", schema = "public")
public class Bike {

    @Id
    @Column(name = "BIKE_ID")
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bikeId;

    @OneToOne(targetEntity = Status.class)
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @Setter
    @Getter
    private Status status;

    @Setter
    @Getter
    @Column(name = "LATITUDE")
    private Double latitude;

    @Setter
    @Getter
    @Column(name = "LONGITUDE")
    private Double longitude;

}
