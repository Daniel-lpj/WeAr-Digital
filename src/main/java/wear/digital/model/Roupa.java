package wear.digital.model;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "roupa")
public class Roupa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roupa_id")
    private Long roupa_id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "preco")
    @JsonProperty(value = "saldo_inicial", access = JsonProperty.Access.WRITE_ONLY)
    private BigDecimal preco;

    @Column(name = "cor")
    private ArrayList<String> cor;

    @Column(name = "tamanho")
    private ArrayList<String> tamanho;

    // @ManyToOne
    // @JoinColumn(name = "semente_id")
    // private Sementes semente;

    // @ManyToOne
    // @JoinColumn(name = "usuario_id")
    // private Usuario usuario;

}
