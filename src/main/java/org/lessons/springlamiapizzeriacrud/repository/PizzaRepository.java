package org.lessons.springlamiapizzeriacrud.repository;

import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    // QUERY CUSTOM:
    // metodo per cercare le pizze con titolo uguale a quello passato
    Page<Pizza> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
