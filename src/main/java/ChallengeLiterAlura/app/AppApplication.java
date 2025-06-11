package ChallengeLiterAlura.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception{
		System.out.println(">>> MÉTODO RUN INICIADO <<<"); // <-- ¿Esto aparece en la consola?
		var consumoAPI = new ConsumoAPI();
		// var json = consumoAPI.obtenerDatos("https://coffee.alexflipnote.dev/random.json");
		var json = consumoAPI.obtenerDatos("https://gutendex.com/books/?search=frankenstein");
		System.out.println(json);
		System.out.println("Este es mi json: "+json);
	}
}
