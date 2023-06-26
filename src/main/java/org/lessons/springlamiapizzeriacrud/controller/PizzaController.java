package org.lessons.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.lessons.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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

    // metodo che restituisce la pagina contenente il form di creazione di una nuova pizza
    @GetMapping("/create")
    public String create(Model model) {
        // aggiungo al model l'attributo contenente un oggetto pizza vuoto
        model.addAttribute("pizza", new Pizza());
        return "/pizza/create"; // restituisco il nome del template della view in cui vi è il form di creazione
    }

    // metodo che gestisce la richiesta in post del form con i dati della pizza
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
        // i dati della pizza sono dentro all'oggetto formPizza (@ModelAtrribute("pizza") è un altro modo per scrivere model.addAttribute sottoforma di annotation)
        // verifico se in validazione ci sono stati errori
        if (bindingResult.hasErrors()) {
            // ci sono stati errori per cui restituisco la view della create con i campi precompilati
            return "/pizza/create";
        }
        // setto il timestamp di creazione (lo creo automaticamente senza far inserire all'utente data e ora di creazione)
        formPizza.setCreatedAt(LocalDateTime.now());
        // persisto formPizza su database
        pizzaRepository.save(formPizza); // metodo save fa una create sql se l'oggetto con quella PRIMARY KEY non esiste, altrimenti fa l'update
        // se l'operazione va a buon fine rimando alla lista delle pizze
        return "redirect:/pizzas";
    }
}
