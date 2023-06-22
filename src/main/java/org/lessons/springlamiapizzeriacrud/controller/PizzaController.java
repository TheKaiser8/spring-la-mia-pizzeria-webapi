package org.lessons.springlamiapizzeriacrud.controller;

import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.lessons.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    // dipende da PizzaRepository
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model) {
        // recupero l'elenco delle pizze dal database
        List<Pizza> pizzas = pizzaRepository.findAll();
        // passo la lista delle pizze alla view
        model.addAttribute("pizzaList", pizzas);
        // restituisco il nome del template della view
        return "pizza/index";
    }
}
