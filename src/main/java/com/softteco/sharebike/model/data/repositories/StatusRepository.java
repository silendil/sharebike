package com.softteco.sharebike.model.data.repositories;

import com.softteco.sharebike.model.data.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, Long> {

    Status findStatusByStatus(String status);
}
