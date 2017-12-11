package com.hubrick.repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface for repository access
 *
 * @param <T> Entity type
 *
 * @author eric.nascimento
 */
public interface Repository<T> {

    List<T> findAll();

    Optional<T> findOne(Integer id);

}
