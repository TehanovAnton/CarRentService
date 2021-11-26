package com.example.carrentservice.repository;

import com.example.carrentservice.models.Rent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentRepository extends CrudRepository<Rent, Long> {
    List<Rent> findAllByUser_id(Integer user_id);
    Rent findById(Integer id);
}
