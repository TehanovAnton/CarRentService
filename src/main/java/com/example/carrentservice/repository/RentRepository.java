package com.example.carrentservice.repository;

import com.example.carrentservice.models.Rents;
import com.example.carrentservice.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface RentRepository extends CrudRepository<Rents, Long> {
}
