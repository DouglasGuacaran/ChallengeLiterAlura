package ChallengeLiterAlura.app.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    @Id
    @JsonAlias("id")
    private Integer id;

    @JsonAlias("title")
    private String titulo;

    @ElementCollection
    @JsonAlias("languages")
    private List<String> languages;

    @JsonAlias("download_count")
    private Integer descargas;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    @JsonAlias("authors")
    private List<Author> autores;

}
