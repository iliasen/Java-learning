package by.iba.springproject.controller;

import by.iba.springproject.model.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;


@Controller
public class MainController {
    private static ArrayList<User> users = new ArrayList<User>();

    static {
        users.add(new User("Olga", "Pertova"));
        users.add(new User("Ivan", "Ivanov"));
    }
    @Value("${welcome.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);
        return modelAndView;
    }
    @RequestMapping(value = {"/users"}, method = RequestMethod.GET)
    public ModelAndView personList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        model.addAttribute("users", users);
        return modelAndView;
    }

}

