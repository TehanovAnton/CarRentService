package com.example.carrentservice.controllers;

import com.example.carrentservice.models.Car;
import com.example.carrentservice.models.Rent;
import com.example.carrentservice.models.RentForm;
import com.example.carrentservice.repository.CarRepository;
import com.example.carrentservice.repository.RentRepository;
import com.example.carrentservice.services.RentsControllerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@RestController
public class RentsController extends ApplicationController {
    @Autowired
    private RentRepository rentsRepository;

    @Autowired
    private CarRepository carRepository;

    private static RentsControllerServices services;
    static {
        services = new RentsControllerServices();
    }

    @GetMapping(value = { "/rent/new" })
    public ModelAndView NewForm(Model model) {
        Car car = new Car("maclaren", 1000);
        carRepository.save(car);

        services.AddRentFormAttr(model);
        return services.ModelAndView("RentNew");
    }

    @PostMapping(value = { "/rent/create" })
    public void Create(Model model, @ModelAttribute("rentForm") RentForm rentForm, HttpServletResponse response) throws IOException, ParseException {
        Rent rent = services.rentByForm(rentForm, carRepository);
        services.SaveRent(rentsRepository, rent);
        response.sendRedirect("/user/home");
    }

    @RequestMapping(value = "rent/update{rent_id}", method = RequestMethod.GET)
    public String rentDetail(Model model, @RequestParam(value = "rent_id") Integer rentId) {
        Rent rent = rentsRepository.findById(rentId);
        model.addAttribute("rentById", rent);
        return "carDetail";
    }

    @GetMapping("/rent/update")
    public ModelAndView UpdateForm(Model model) {
        services.AddRentFormAttr(model);
        return services.ModelAndView("RentUpdate");
    }
}
