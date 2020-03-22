package com.example.demo.controller;


import com.example.demo.models.Tourist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.validation.Valid;
import java.util.List;


import java.util.List;

@Controller
public class TouristController {
    private SessionFactory sessionFactory;

    @Autowired
    public TouristController(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }



    @GetMapping("/tourists")
    public String getTouristList(Model model){
        Session session = sessionFactory.openSession();

        List<Tourist> tourists = session.createQuery("FROM Tourist",Tourist.class).getResultList();

        model.addAttribute("tourists",tourists);
        model.addAttribute("newTourist",new Tourist());

        return "tourists";
    }

    @PostMapping("/add")
    public String addNewTourist(@Valid Tourist tourist, BindingResult result){
        if(!result.hasErrors()){
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(tourist);
            session.getTransaction().commit();
            session.close();

        }

        return "redirect:/tourists";

    }

    @GetMapping("delete/{id}")
    public String deleteTourist(@PathVariable("id") Long id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("delete Tourist where id = :ID");
        q.setParameter("ID",id);
        q.executeUpdate();

        session.getTransaction().commit();
        session.close();

        return "redirect:/tourists";
    }

}
