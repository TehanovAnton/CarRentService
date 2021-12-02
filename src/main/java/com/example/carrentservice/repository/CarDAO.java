package com.example.carrentservice.repository;
import com.example.carrentservice.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarDAO extends JpaRepository<Car, Long> {

    @Query("from Car as c where c.id not in (select b.car.id from BorrowedDate as b)")
    List<Car> newCars();

    @Query("select c.id from Car as c where c.id not in (select bd.car.id from BorrowedDate as bd)")
    List<Long> newCarsId();

    List<Car> findAll();

    Optional<Car> findById(Long id);
}
