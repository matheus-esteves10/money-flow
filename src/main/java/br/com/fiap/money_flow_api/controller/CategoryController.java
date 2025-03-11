package br.com.fiap.money_flow_api.controller;

import br.com.fiap.money_flow_api.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private List<Category> repository = new ArrayList<>();

    @GetMapping()
    public List<Category> getCategories() {
        return repository;
    }

    @PostMapping()
    public ResponseEntity<Category> create(@RequestBody Category category) {
        logger.info("Cadastrando... " + category.getName());
        repository.add(category);
        return ResponseEntity.status(201).body(category);
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        logger.info("Buscando " + id);

        return getCategory(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById (@PathVariable Long id) {
        logger.info("Deletando " + id);
        repository.remove(getCategory(id));
    }

    @PutMapping
    public Category editById (@PathVariable Long id, @RequestBody Category category) {
        logger.info("Editando " + id);
        repository.remove(getCategory(id));
        category.setId(id);
        repository.add(category);

        return category;
    }

    private Category getCategory(Long id){
        return repository.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }

}
