package br.com.fiap.money_flow_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Transactional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo é obrigatório")
    @Size(min = 5, max = 255, message = "O campo name deve ter entre 5 e 255 caracteres")
    private String description;

    @Positive(message = "O campo amount deve ser positivo")
    private BigDecimal amount;

    @PastOrPresent (message = "O campo date deve ser uma data passada ou hoje" )
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo type é obrigatório")
    private TransactionType type;

    @NotNull(message = "O campo category é obrigatório")
    @ManyToOne
    private Category category;
}
