package org.lessons.springlamiapizzeriacrud.api;

import jakarta.validation.Valid;
import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.lessons.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController // identifica il controller come REST
@CrossOrigin // permette di chiamare la api anche con un dominio diverso da quello dell'applicazione
@RequestMapping("/api/v1/pizzas")
public class PizzaRestController {

    @Autowired
    private PizzaRepository pizzaRepository;

    // servizio per avere la lista delle pizze con parametro opzionale
    @GetMapping
    public List<Pizza> index(@RequestParam Optional<String> keyword) {
        if (keyword.isEmpty()) {
            return pizzaRepository.findAll();
        } else {
            return pizzaRepository.findByNameContainingIgnoreCase(keyword.get());
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
        return pizzaRepository.save(pizza);
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
