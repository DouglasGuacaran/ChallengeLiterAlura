package ChallengeLiterAlura.app.repository;

import ChallengeLiterAlura.app.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    Optional<Author> findByNombre(String nombre);
}
