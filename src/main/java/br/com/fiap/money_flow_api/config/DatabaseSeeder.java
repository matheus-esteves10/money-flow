package br.com.fiap.money_flow_api.config;

import br.com.fiap.money_flow_api.model.Category;
import br.com.fiap.money_flow_api.model.User;
import br.com.fiap.money_flow_api.repository.CategoryRepository;
import br.com.fiap.money_flow_api.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct //metodo executa depois do construtor (toda vez que inicia roda pelo spring inicializar o @Configuration)
    public void init(){
        var categories = List.of(
                Category.builder().name("Educação").icon("Book").build(),
                Category.builder().name("Transporte").icon("Bus").build(),
                Category.builder().name("Saúde").icon("Heart").build(),
                Category.builder().name("Moradia").icon("House").build()

        );

        categoryRepository.saveAll(categories);

        userRepository.saveAll(List.of(
                User.builder().email("Joao@fiap.com").password(passwordEncoder.encode("12345")).role("ADMIN").build(),
                User.builder().email("maria@fiap.com").password(passwordEncoder.encode("12345")).role("USER").build()
        ));
    }
}
