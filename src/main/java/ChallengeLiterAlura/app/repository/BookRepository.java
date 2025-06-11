package ChallengeLiterAlura.app.repository;

import ChallengeLiterAlura.app.entidades.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Integer > {

}
