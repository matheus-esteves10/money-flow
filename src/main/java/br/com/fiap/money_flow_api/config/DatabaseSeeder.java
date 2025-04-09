package br.com.fiap.money_flow_api.config;

import br.com.fiap.money_flow_api.model.Category;
import br.com.fiap.money_flow_api.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct //metodo executa depois do construtor (toda vez que inicia roda pelo spring inicializar o @Configuration)
    public void init(){
        var categories = List.of(
                Category.builder().name("Educação").icon("Book").build(),
                Category.builder().name("Transporte").icon("Bus").build(),
                Category.builder().name("Saúde").icon("Heart").build(),
                Category.builder().name("Moradia").icon("House").build()

        );

        categoryRepository.saveAll(categories);
    }
}
