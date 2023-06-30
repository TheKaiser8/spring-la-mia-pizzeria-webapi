package org.lessons.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.springlamiapizzeriacrud.messages.AlertMessage;
import org.lessons.springlamiapizzeriacrud.messages.AlertMessageType;
import org.lessons.springlamiapizzeriacrud.model.Ingredient;
import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.lessons.springlamiapizzeriacrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    // metodo index gestisce sia la lista degli ingredienti presenti sul database che il form per creare o modificare un ingrediente
    // passo un parametro opzionale con l'id dell'ingrediente che voglio modificare
    @GetMapping
    public String index(Model model, @RequestParam("editIngredient") Optional<Integer> ingredientId) {
        // recupero da database tutti gli ingredienti
        List<Ingredient> ingredientList = ingredientRepository.findAll(); // findAll restituisce una lista vuota se non trova elementi, non restituisce null
        // passo al model un attributo ingredients con tutti gli ingredienti
        model.addAttribute("ingredients", ingredientList);
        // definisco un oggetto Ingredient
        Ingredient ingredientObj;
        // se ho il parametro ingredientId cerco l'ingrediente corrispondente su database
        if (ingredientId.isPresent()) {
            Optional<Ingredient> ingredientDb = ingredientRepository.findById(ingredientId.get());
            // se è presente l'oggetto su db, ingredientObj diventa l'oggetto ingredientDb
            if (ingredientDb.isPresent()) {
                ingredientObj = ingredientDb.get();
            } else {
                // se NON è presente l'oggetto su db, ingredientObj diventa un nuovo oggetto vuoto
                ingredientObj = new Ingredient();
            }
        } else {
            // se NON ho il parametro opzionale, ingredientObj diventa un nuovo oggetto vuoto
            ingredientObj = new Ingredient();
        }
        // passo al model un attributo ingredientObj per mappare il form su un oggetto di tipo Ingredient
        model.addAttribute("ingredientObj", ingredientObj);
        return "/ingredients/index";
    }

    // metodo save per salvare su database l'attributo formIngredient
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("ingredientObj") Ingredient formIngredient, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        // verifichiamo se ci sono errori
        if (bindingResult.hasErrors()) {
            // per ricreare correttamente la index, oltre a ingredientObj, dobbiamo passare al model anche la lista degli ingredienti
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "/ingredients/index";
        }
        Integer preFormIngredientId = formIngredient.getId(); // ottengo id del form PRIMA del suo salvataggio
        // dopo la validazione salvo il formIngredient
        ingredientRepository.save(formIngredient);
        Integer postFormIngredientId = formIngredient.getId(); // ottengo id del form DOPO il suo salvataggio
        // creo stringa con confronto tramite operatore ternario che mi permette di personalizzare il messaggio di modifica/creazione
        String action = preFormIngredientId != null && preFormIngredientId.equals(postFormIngredientId) ? " aggiornato" : " creato";
        // aggiungo un messaggio di successo come flash attribute utilizzando un classe CUSTOM per personalizzare i messaggi degli alert
        redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.SUCCESS, "L'ingrediente " + "\"" + formIngredient.getName() + "\"" + " è stato" + action + "!"));
        // fa la redirect alla index
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        // prima di eliminare l'ingrediente lo dissocio da tutte le pizze
        Optional<Ingredient> result = ingredientRepository.findById(id);
        if (result.isEmpty()) {
            // ritorno un HTTP Status 404 Not Found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'ingrediente con ID " + id + " non è stato trovato");
        }
        // ottengo l'oggetto ingrediente da eliminare
        Ingredient ingredientToDelete = result.get();
        // itero su ogni pizza associata all'ingrediente per eliminare l'ingrediente
        for (Pizza pizza : ingredientToDelete.getPizzas()) {
            pizza.getIngredients().remove(ingredientToDelete);
        }
        ingredientRepository.deleteById(id);
        // aggiungo un messaggio di successo come flash attribute utilizzando un classe CUSTOM per personalizzare i messaggi degli alert
        redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.SUCCESS, "L'ingrediente " + "\"" + ingredientToDelete.getName() + "\"" + " è stato cancellato!"));
        return "redirect:/ingredients";
    }
}
