package org.lessons.springlamiapizzeriacrud.service;

import org.lessons.springlamiapizzeriacrud.dto.PizzaForm;
import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.lessons.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    // metodo che salva una nuova pizza a partire da quella passata come parametro
    public Pizza create(Pizza pizza) {
        // creo pizza da salvare
        Pizza pizzaToPersist = new Pizza();
        // genero il timestamp di createdAt
        pizzaToPersist.setCreatedAt(LocalDateTime.now());
        // copio tutti i campi della pizza che mi interessano
        pizzaToPersist.setId(pizza.getId());
        pizzaToPersist.setName(pizza.getName());
        pizzaToPersist.setDescription(pizza.getDescription());
        pizzaToPersist.setPhotoUrl(pizza.getPhotoUrl());
        pizzaToPersist.setPrice(pizza.getPrice());
        pizzaToPersist.setIngredients(pizza.getIngredients());
        pizzaToPersist.setCover(pizza.getCover());
        // persisto la pizza
        return pizzaRepository.save(pizzaToPersist);
    }

    // metodo per creare una nuova pizza a partire da un DTO PizzaForm
    public Pizza create(PizzaForm pizzaForm) {
        // converto il PizzaForm in una Pizza
        Pizza pizza = mapPizzaFormToPizza(pizzaForm);
        // salvo la pizza su db
        return create(pizza);
    }

    // metodo per convertire un PizzaForm in una Pizza
    private Pizza mapPizzaFormToPizza(PizzaForm pizzaForm) {
        // creo nuova pizza vuota
        Pizza pizza = new Pizza();
        // setto i campi che mi interessano
        pizza.setId(pizzaForm.getId());
        pizza.setName(pizzaForm.getName());
        pizza.setDescription(pizzaForm.getDescription());
        pizza.setPhotoUrl(pizzaForm.getPhotoUrl());
        pizza.setPrice(pizzaForm.getPrice());
        pizza.setIngredients(pizzaForm.getIngredients());
        // converto il campo cover
        byte[] coverBytes = multipartFileToByteArray(pizzaForm.getCoverFile());
        pizza.setCover(coverBytes);

        return pizza;
    }

    // metodo per convertire un multipart file in byte array
    private byte[] multipartFileToByteArray(MultipartFile mpf) {
        // dichiaro e inizializzo array di byte che devo restituire
        byte[] bytes = null;
        if (mpf != null && !mpf.isEmpty()) {
            try {
                bytes = mpf.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
}
