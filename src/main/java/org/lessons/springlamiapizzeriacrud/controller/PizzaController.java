package org.lessons.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.springlamiapizzeriacrud.messages.AlertMessage;
import org.lessons.springlamiapizzeriacrud.messages.AlertMessageType;
import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.lessons.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    // dipende da PizzaRepository
    @Autowired
    private PizzaRepository pizzaRepository;

    // READ METHODS
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
        /*
        // SOLUZIONE 1: if/else e metodo isPresent()
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
        } */

        // SOLUZIONE 2: if e metodo isEmpty() con utility method
        // REFACTORING con utility method (principio DRY)
        Pizza pizza = getPizzaById(pizzaId);
        // recupero i dati della pizza e passo la pizza alla view
        model.addAttribute("pizza", pizza); // passo tutto l'oggetto, non solo l'id

        // ritorno il nome del template della view
        return "/pizza/show";
    }

    // CREATE METHODS
    // metodo che restituisce la pagina contenente il form di creazione di una nuova pizza
    @GetMapping("/create")
    public String create(Model model) {
        // aggiungo al model l'attributo contenente un oggetto pizza vuoto
        model.addAttribute("pizza", new Pizza());
//        return "/pizza/create"; // restituisco il nome del template della view in cui vi è il form di creazione
        return "/pizza/edit"; // restituisco un template unico sia per la create che per la edit
    }

    // metodo che gestisce la richiesta in post del form con i dati della pizza
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // i dati della pizza sono dentro all'oggetto formPizza (@ModelAtrribute("pizza") è un altro modo per scrivere model.addAttribute sottoforma di annotation)
        // verifico se in validazione ci sono stati errori
        if (bindingResult.hasErrors()) {
            // ci sono stati errori per cui restituisco la view della create con i campi precompilati
//            return "/pizza/create";
            return "/pizza/edit"; // restituisco un template unico sia per la create che per la edit
        }
        // setto il timestamp di creazione (lo creo automaticamente senza far inserire all'utente data e ora di creazione)
        formPizza.setCreatedAt(LocalDateTime.now());
        // persisto formPizza su database
        pizzaRepository.save(formPizza); // metodo save fa una create sql se l'oggetto con quella PRIMARY KEY non esiste, altrimenti fa l'update
        // aggiungo un messaggio di successo come flash attribute utilizzando un classe CUSTOM per personalizzare i messaggi degli alert
        redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.SUCCESS, "La pizza " + "\"" + formPizza.getName() + "\"" + " è stata creata!"));
        // se l'operazione va a buon fine rimando alla lista delle pizze
        return "redirect:/pizzas";
    }

    // EDIT METHODS
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer pizzaId, Model model) {
        /*
        // SOLUZIONE 1: if/else e metodo isPresent()
        // verificare se su database esiste la pizza con quell'id
        Optional<Pizza> result = pizzaRepository.findById(pizzaId);
        if (result.isPresent()) {
            // recupero i dati della pizza e passo la pizza alla view
            model.addAttribute("pizza", result.get()); // passo tutto l'oggetto, non solo l'id
            // ritorno il nome del template della view
            return "/pizza/edit";
        } else {
            // ritorno un HTTP Status 404 Not Found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La pizza con ID " + pizzaId + " non è stata trovata");
        } */

        // SOLUZIONE 2: if e metodo isEmpty()
        // verificare se su database esiste la pizza con quell'id
        /*
        Optional<Pizza> result = pizzaRepository.findById(pizzaId);
        if (result.isEmpty()) {
            // ritorno un HTTP Status 404 Not Found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La pizza con ID " + pizzaId + " non è stata trovata");
        }
        // recupero i dati della pizza e passo la pizza alla view
        model.addAttribute("pizza", result.get()); // passo tutto l'oggetto, non solo l'id
        */

        // REFACTORING con utility method (principio DRY)
        Pizza pizza = getPizzaById(pizzaId);
        // recupero i dati della pizza e passo la pizza alla view
        model.addAttribute("pizza", pizza); // passo tutto l'oggetto, non solo l'id

        // ritorno il nome del template della view
        return "/pizza/edit";
    }

    @PostMapping("edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // cerco la pizza per id
        Pizza pizzaToEdit = getPizzaById(id); // vecchia versione della pizza
        // formPizza è la nuova versione della pizza
        if (bindingResult.hasErrors()) {
            // se ci sono errori ritorno il template del form
            return "pizza/edit";
        }
        // trasferisco su formPizza tutti i valori dei campi che non sono presenti o non sono modificabili nel form (altrimenti vengono persi)
        formPizza.setId(pizzaToEdit.getId());
        formPizza.setCreatedAt(pizzaToEdit.getCreatedAt());
        // salvo i dati
        pizzaRepository.save(formPizza);
        // aggiungo un messaggio di successo come flash attribute utilizzando un classe CUSTOM per personalizzare i messaggi degli alert
        redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.SUCCESS, "La pizza " + "\"" + formPizza.getName() + "\"" + " è stata aggiornata!"));
        return "redirect:/pizzas";
    }

    // DELETE METHOD
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        // RedirectAttributes: interfaccia che estende model e ci consente di aggiungere un attributo (esempio: flashAttribute)
        // verifichiamo che esiste La pizza con quell'id
        Pizza pizzaToDelete = getPizzaById(id);
        // lo cancelliamo
        pizzaRepository.delete(pizzaToDelete);
        // aggiungo un messaggio di successo come flash attribute
//        redirectAttributes.addFlashAttribute("message", "La pizza " + "\"" + pizzaToDelete.getName() + "\"" + " è stata cancellata!");
        // aggiungo un messaggio di successo come flash attribute utilizzando un classe CUSTOM per personalizzare i messaggi degli alert
        redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.SUCCESS, "La pizza " + "\"" + pizzaToDelete.getName() + "\"" + " è stata cancellata!"));
        // facciamo la redirect alla lista delle pizze
        return "redirect:/pizzas";
    }

    // UTILITY METHODS
    // metodo per selezionare la pizza da database o tirare un'eccezione
    private Pizza getPizzaById(Integer id) {
        // verificare se su database esiste la pizza con quell'id
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isEmpty()) {
            // ritorno un HTTP Status 404 Not Found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La pizza con ID " + id + " non è stata trovata");
        }

        // ritorno il risultato dell'id della pizza
        return result.get();
    }
}
