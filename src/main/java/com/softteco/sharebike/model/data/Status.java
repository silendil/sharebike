package com.softteco.sharebike.model.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bikes_statuses", schema = "public")
public class Status {

    @Id
    @Column(name = "STATUS_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long statusId;

    @Column(name = "STATUS")
    @Setter
    @Getter
    private String status;
}
