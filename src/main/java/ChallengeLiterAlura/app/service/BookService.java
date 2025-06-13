package ChallengeLiterAlura.app.service;

import ChallengeLiterAlura.app.dto.AutorDTO;
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

    public List<Book> fetchAndSaveBooks() throws Exception {
        String baseUrl = "https://gutendex.com/books/";
        String nextUrl = baseUrl;
        List<Book> savedBooks = new ArrayList<>();

        while (nextUrl != null) {
            String json = restTemplate.getForObject(nextUrl, String.class);
            GutendexResponse respuesta = objectMapper.readValue(json, GutendexResponse.class);

            for (DatosLibro dto : respuesta.getResults()) {
                if (bookRepository.existsById(dto.getId())) {
                    continue; // Evita duplicados
                }

                Book book = new Book();
                book.setId(dto.getId());
                book.setTitulo(dto.getTitulo());
                book.setDescargas(dto.getDescargas());
                book.setIdiomas(dto.getIdiomas());
                book.setImagenUrl(dto.getImagenUrl());

                List<Author> persistedAuthors = new ArrayList<>();
                for (AutorDTO autorDTO : dto.getAutores()) {
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

            nextUrl = respuesta.getNext();
        }

        return savedBooks;
    }
}