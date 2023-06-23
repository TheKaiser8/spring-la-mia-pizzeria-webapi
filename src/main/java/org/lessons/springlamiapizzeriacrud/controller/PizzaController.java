package org.lessons.springlamiapizzeriacrud.controller;

import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.lessons.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    // dipende da PizzaRepository
    @Autowired
    private PizzaRepository pizzaRepository;

    // metodo che può ricevere un parametro OPZIONALE (da query string)
    @GetMapping
    public String index(@RequestParam(name = "q", required = false) String searchString, Model model) {
        // q è il nome che ho dato al parametro in get e nella URL devo richiamare questo nome (esempio: ?q=dune)
        List<Pizza> pizzas;
        if (searchString == null || searchString.isBlank()) {
            // se NON ho il parametro esegue la query generica e recupero la lista delle pizze dele database
            pizzas = pizzaRepository.findAll();
        } else {
            // se ho il parametro searchString faccio la query per filtrare la lista
            pizzas = pizzaRepository.findByNameContainingIgnoreCase(searchString);
        }

        // passo la lista delle pizze alla view
        model.addAttribute("pizzaList", pizzas);
        // aggiungo un altro attributo al model per mantenere il valore della input dopo l'invio della ricerca filtrata
        model.addAttribute("searchInput", searchString == null ? "" : searchString);
        // restituisco il nome del template della view
        return "pizza/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer pizzaId) {
        // cerco su database la pizza con id corrispondente all'id del path
        Optional<Pizza> result = pizzaRepository.findById(pizzaId);
        if (result.isPresent()) {
            // se il result è presente nel database aggiungo l'oggetto pizza al model
            model.addAttribute("pizza", result.get());
            // restituisco il nome del template della view
            return "/pizza/show";
        } else {
            // restituisco un HTTP Status 404 Not Found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Errore: la pizza con ID " + pizzaId + " non è stata trovata.");
        }
    }
}
