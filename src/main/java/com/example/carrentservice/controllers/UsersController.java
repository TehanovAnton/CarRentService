package com.example.carrentservice.controllers;

import com.example.carrentservice.models.Rent;
import com.example.carrentservice.models.User;
import com.example.carrentservice.repository.RentRepository;
import com.example.carrentservice.repository.UserRepository;
import com.example.carrentservice.services.RegistrationServices;
import com.example.carrentservice.services.UserControllerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Slf4j
@Controller
@RequestMapping
public class UsersController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentRepository rentRepository;

    private static RegistrationServices registrationServices;
    private static UserControllerServices services;

    static
    {
        registrationServices = new RegistrationServices();
        services = new UserControllerServices();
    }
    
    @GetMapping("/user/home")
    public ModelAndView Home(Model model) {
        List<Rent> rents = services.Rents(rentRepository);
        services.AddAttr(model, rents);
        return services.ModelAndView("Home");
    }

    @GetMapping(value = { "/user/new" })
    public ModelAndView New(Model model)
    {
        services.AddNewUserAttr(model);
        return services.ModelAndView("UsersNew");
    }

    @PostMapping(value = { "/user/create" })
    public void Create(Model model, @ModelAttribute("user") User user, HttpServletResponse response) throws IOException {
        registrationServices.Register(userRepository, user);
        response.sendRedirect("/rent/new");
    }
}
