package ChallengeLiterAlura.app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Book {

    @Id
    private Integer id;

    @Column(name = "titulo", length = 500)
    private String titulo;

    @ElementCollection
    private List<String> idiomas;

    @Column(length = 1000)
    private String imagenUrl;

    private Integer descargas;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Author> autores;
}
