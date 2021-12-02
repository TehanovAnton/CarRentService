package com.example.carrentservice.repository;

import com.example.carrentservice.models.AvailableCarsResult;
import com.example.carrentservice.models.BorrowedDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Calendar;
import java.util.List;

@Repository
public interface BorrowedDateDAO extends JpaRepository<BorrowedDate, Long> {
    BorrowedDate findByCustomerId(Long id);
    BorrowedDate findByCarId(Long id);
    List<BorrowedDate> findAll();

    @Query("select b.car.id " +
            "from BorrowedDate as b "+
            "where :startDate not between b.startDate and b.endDate "+
            "and :endDate not between b.startDate and b.endDate "+
            "and b.car.id NOT IN (" +
                "select DISTINCT bd.car.id "+
                "from BorrowedDate bd "+
                "where :startDate between bd.startDate and bd.endDate "+
                "OR :endDate between bd.startDate and bd.endDate) " +
            "group by b.car.id")
    List<Long> checkAvailableCars(@Param("startDate") Calendar startDate,
                                  @Param("endDate") Calendar endDate);


    @Query("select b.car.id " +
            "from BorrowedDate as b "+
            "where :startDate not between b.startDate and b.endDate "+
            "and :endDate not between b.startDate and b.endDate "+
            "and b.car.id = :carId "+
            "and b.car.id NOT IN (" +
                "select DISTINCT bd.car.id "+
                "from BorrowedDate bd "+
                "where :startDate between bd.startDate and bd.endDate "+
                "OR :endDate between bd.startDate and bd.endDate) " +
            "group by b.car.id ")
    List<Long> checkAvailableCarById(@Param("startDate") Calendar startDate,
                                    @Param("endDate") Calendar endDate,
                                    @Param("carId") Long id);


}
