package org.lessons.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.springlamiapizzeriacrud.model.Ingredient;
import org.lessons.springlamiapizzeriacrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    // metodo index gestisce sia la lista degli ingredienti presenti sul database che il form per creare o modificare un ingrediente
    @GetMapping
    public String index(Model model) {
        // recupero da database tutti gli ingredienti
        List<Ingredient> ingredientList = ingredientRepository.findAll(); // findAll restituisce una lista vuota se non trova elementi, non restituisce null
        // passo al model un attributo ingredients con tutti gli ingredienti
        model.addAttribute("ingredients", ingredientList);
        // passo al model un attributo ingredientObj per mappare il form su un oggetto di tipo Ingredient
        model.addAttribute("ingredientObj", new Ingredient());
        return "/ingredients/index";
    }

    // metodo save per salvare su database l'attributo formIngredient
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("ingredientObj") Ingredient formIngredient, BindingResult bindingResult, Model model) {
        // verifichiamo se ci sono errori
        if (bindingResult.hasErrors()) {
            // per ricreare correttamente la index, oltre a ingredientObj, dobbiamo passare al model anche la lista degli ingredienti
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "/ingredients/index";
        }
        // dopo la validazione salvo il formIngredient
        ingredientRepository.save(formIngredient);
        // fa la redirect alla index
        return "redirect:/ingredients";
    }
}
