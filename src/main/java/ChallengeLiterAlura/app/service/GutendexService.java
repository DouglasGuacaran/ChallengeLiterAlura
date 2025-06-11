package ChallengeLiterAlura.app.service;

import ChallengeLiterAlura.app.dto.DatosLibro;
import ChallengeLiterAlura.app.model.Author;
import ChallengeLiterAlura.app.model.Book;
import ChallengeLiterAlura.app.repository.AuthorRepository;
import ChallengeLiterAlura.app.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GutendexService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Book> fetchAndSaveBooks(String searchTerm) throws Exception {
        String url = "https://gutendex.com/books/?search=" + searchTerm;
        String json = restTemplate.getForObject(url, String.class);

        GutendexResponse respuesta = objectMapper.readValue(json, GutendexResponse.class);

        List<Book> savedBooks = new ArrayList<>();

        for (DatosLibro dto : respuesta.getResults()) {
            Book book = new Book();
            book.setId(dto.getId());
            book.setTitulo(dto.getTitulo());
            book.setDescargas(dto.getDescargas());
            book.setLanguages(dto.getIdiomas());

            List<Author> persistedAuthors = new ArrayList<>();
            for (DatosLibro.AutorDTO autorDTO : dto.getAutores()) {
                Author existing = authorRepository.findByNombre(autorDTO.getNombre()).orElse(null);
                if (existing == null) {
                    Author nuevoAutor = new Author();
                    nuevoAutor.setNombre(autorDTO.getNombre());
                    nuevoAutor.setAnioNacimiento(autorDTO.getAnioNacimiento());
                    nuevoAutor.setAnioFallecimiento(autorDTO.getAnioFallecimiento());
                    existing = authorRepository.save(nuevoAutor);
                }
                persistedAuthors.add(existing);
            }

            book.setAutores(persistedAuthors);
            savedBooks.add(bookRepository.save(book));
        }

        return savedBooks;
    }
}
