package com.hubrick.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    List<T> findAll();

    Optional<T> findOne(Integer id);

}
