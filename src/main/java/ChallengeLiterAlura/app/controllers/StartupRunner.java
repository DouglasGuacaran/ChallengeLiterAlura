package ChallengeLiterAlura.app.controllers;

import ChallengeLiterAlura.app.service.BookService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final BookService bookService;

    public StartupRunner(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Cambia el término de búsqueda por lo que necesites
        bookService.fetchAndSaveBooks("Frankenstein");
    }
}
