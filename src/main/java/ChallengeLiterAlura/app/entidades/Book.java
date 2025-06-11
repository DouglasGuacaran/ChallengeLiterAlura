package ChallengeLiterAlura.app.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Book {
    @Id
    private int id;

    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id") // clave foránea en Author
    private List<Author> authors;
}
