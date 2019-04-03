package com.balaji.rms.controllers;

import com.balaji.rms.domain.IndexFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Controller
public class HomeController {

    @Autowired
    private Environment env;
    private String result;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model){
        model.addAttribute("formBean",new IndexFormBean());
        return "index";
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String calculate(@Valid IndexFormBean formBean, Model model){

        model.addAttribute("formBean",formBean);
        result= String.format("There are %d days between the Start Date and End Date",countDays(formBean.getStartDate(),formBean.getEndDate()));
        model.addAttribute("result",result);
        return "index";
    }

    private long countDays(String  startDate, String endDate){
        LocalDate firstDate, secondDate;
        firstDate = LocalDate.parse(startDate);
        secondDate = LocalDate.parse(endDate);
        return DAYS.between(firstDate,secondDate);
    }

    @ExceptionHandler(BindException.class)
    public String handleException(BindException exception, Model model){
        result = env.getProperty(exception.getGlobalError().getDefaultMessage());
        model.addAttribute("result",result);
        return "error";
    }

    public Environment getEnv() {
        return env;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }
}
