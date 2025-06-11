package ChallengeLiterAlura.app.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonAlias("name")
    private String nombre;

    @JsonAlias("birth_year")
    private Integer anioNacimiento;

    @JsonAlias("death_year")
    private Integer anioFallecimiento;

    @JsonIgnore
    @ManyToMany(mappedBy = "autores", fetch = FetchType.LAZY)
    private List<Book> libros;

}
