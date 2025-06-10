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
		System.out.println("¡Hola, Mundo!");

		var consumoAPI = new ConsumoAPI();
		// var json = consumoAPI.obtenerDatos("https://coffee.alexflipnote.dev/random.json");
		var json = consumoAPI.obtenerDatos("http://gutendex.com/books");
		System.out.println(json);

	}
}
