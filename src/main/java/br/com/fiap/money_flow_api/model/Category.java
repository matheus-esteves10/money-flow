package br.com.fiap.money_flow_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@EqualsAndHashCode  -> @Data gera tudo isso

@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank (message = "O campo é obrigatório")
    @Size(min = 3, max = 255, message = "O campo name deve ter entre 3 e 255 caracteres")
    private String name;

    @NotBlank (message = "O campo é obrigatório")
    @Pattern(regexp = "^[A-Z].*", message = "O campo icon deve comecar com a letra maiuscula")
    private String icon;




}
