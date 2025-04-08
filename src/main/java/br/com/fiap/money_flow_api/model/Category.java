package br.com.fiap.money_flow_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Random;

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
    private String name;
    private String icon;




}
