package com.example.carrentservice.services;

import com.example.carrentservice.controllers.SessionsController;
import com.example.carrentservice.models.Rent;
import com.example.carrentservice.models.User;
import com.example.carrentservice.repository.RentRepository;
import lombok.NoArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class UserControllerServices {
    public void AddAttr(Model model, List<Rent> rents) {
        model.addAttribute("rents", rents);
    }

    public List<Rent> Rents(RentRepository rentRepository) {
        List<Rent> rents = rentRepository.findAllByUser_id(SessionsController.getCurrentUser().getId());
        return rents.size() != 0 ? rents : new ArrayList<Rent>();
    }

    public ModelAndView ModelAndView(String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        return modelAndView;
    }

    public void AddNewUserAttr(Model model) {
        User user = new User();
        model.addAttribute("user", user);
    }
}
