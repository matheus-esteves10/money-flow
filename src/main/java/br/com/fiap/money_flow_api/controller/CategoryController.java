package br.com.fiap.money_flow_api.controller;

import br.com.fiap.money_flow_api.model.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping()
    public List<Category> getCategories() {
        return List.of(
                new Category(1L, "Educação", "book"),
                new Category(2L, "Lazer", "casino"),
                new Category(3L, "Salário", "payments")

        );
    }
}
