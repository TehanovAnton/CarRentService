package com.example.carrentservice.controllers;

import com.example.carrentservice.models.AvailableCarsResult;
import com.example.carrentservice.services.BorrowedDateService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class Index {

    private BorrowedDateService borrowedDateService;

    public Index(BorrowedDateService borrowedDateService) {
        this.borrowedDateService = borrowedDateService;
    }

    @GetMapping(value = "/")
    public String index(Model model,
            @RequestParam(value = "start_date", defaultValue = "2000-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar startDate,
            @RequestParam(value = "end_date", defaultValue = "2000-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar endDate) {
        List<AvailableCarsResult> availableCars = borrowedDateService.checkAvailableCars(startDate, endDate);

        model.addAttribute("availableCars", availableCars);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("start_date", format.format(startDate.getTime()));
        model.addAttribute("end_date", format.format(endDate.getTime()));
        return "index";
    }
}
