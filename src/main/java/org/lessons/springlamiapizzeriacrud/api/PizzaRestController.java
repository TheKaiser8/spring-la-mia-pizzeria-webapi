package org.lessons.springlamiapizzeriacrud.api;

import jakarta.validation.Valid;
import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.lessons.springlamiapizzeriacrud.repository.PizzaRepository;
import org.lessons.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController // identifica il controller come REST
@CrossOrigin // permette di chiamare la api anche con un dominio diverso da quello dell'applicazione
@RequestMapping("/api/v1/pizzas")
public class PizzaRestController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaService pizzaService;

    // servizio per avere la lista delle pizze paginata con parametro opzionale di ricerca
    @GetMapping
    public Page<Pizza> index(
            @RequestParam Optional<String> keyword,
            @RequestParam(defaultValue = "2") Integer size,
            @RequestParam(defaultValue = "0") Integer page) {
        // creo un Pageable a partire da size e page
        Pageable pageable = PageRequest.of(page, size);
        if (keyword.isEmpty()) {
            return pizzaRepository.findAll(pageable);
        } else {
            return pizzaRepository.findByNameContainingIgnoreCase(keyword.get(), pageable);
        }
    }

    // servizio per avere la singola pizza
    @GetMapping("/{id}")
    public Pizza get(@PathVariable Integer id) {
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if (pizza.isPresent()) {
            return pizza.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // servizio per creare una nuova pizza (arriva nel Request Body in formato JSON)
    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza) {
        return pizzaService.create(pizza);
    }

    // servizio per modificare/aggiornare una pizza
    @PutMapping("/{id}")
    public Pizza update(@PathVariable Integer id, @Valid @RequestBody Pizza pizza) {
        pizza.setId(id);
        return pizzaRepository.save(pizza);
    }

    // servizio per cancellare una pizza
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        pizzaRepository.deleteById(id);
    }

}
