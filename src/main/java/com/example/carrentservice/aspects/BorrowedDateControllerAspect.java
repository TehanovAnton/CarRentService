package com.example.carrentservice.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BorrowedDateControllerAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @After(value = "execution(* com.example.carrentservice.controllers.BorrowedDateController.borrowedDates(..))")
    public void borrowedDates(JoinPoint joinPoint) {
        System.out.println("show borrowedDates");
    }

    @After("execution(* com.example.carrentservice.controllers.BorrowedDateController.saveBorrowedDate(..))")
    public void saveBorrowedDate(JoinPoint joinPoint) {
        System.out.println("borrowedDates saved");
    }

    @After("execution(* com.example.carrentservice.controllers.BorrowedDateController.editBorrowedDate(..))")
    public void saveEditBorrowedDate(JoinPoint joinPoint) {
        System.out.println("edited borrowedDates saved");
    }

    @Pointcut("execution(* com.example.carrentservice.controllers.BorrowedDateController.borrowedDates*(..))")
    public void borrowedDates(){}

    @After("borrowedDates() && args(carId)")
    public void deleteBorrowedDate(JoinPoint joinPoint, Long carId) {
        System.out.println("delete delete:" + carId.toString());
    }
}
