package ChallengeLiterAlura.app.service;

import ChallengeLiterAlura.app.dto.DatosLibro;
import ChallengeLiterAlura.app.model.Author;
import ChallengeLiterAlura.app.model.Book;
import ChallengeLiterAlura.app.repository.AuthorRepository;
import ChallengeLiterAlura.app.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       RestTemplate restTemplate,
                       ObjectMapper objectMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<Book> fetchAndSaveBooks(String searchTerm) throws Exception {
        String url = "https://gutendex.com/books/?search=" + searchTerm;
        String json = restTemplate.getForObject(url, String.class);

        GutendexResponse response = objectMapper.readValue(json, GutendexResponse.class);

        List<Book> savedBooks = new ArrayList<>();

        for (DatosLibro dto : response.getResults()) {
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
                    nuevoAutor.setAnioFallecimiento(autorDTO.getAnioFallecimiento());
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