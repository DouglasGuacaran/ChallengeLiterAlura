package ChallengeLiterAlura.app;

import ChallengeLiterAlura.app.service.ConsumoAPI;
import ChallengeLiterAlura.app.service.ConvierteDatos;
import ChallengeLiterAlura.app.service.GutendexResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {
	@Configuration
	public class AppConfig {

		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception{
		System.out.println(">>> MÉTODO RUN INICIADO <<<"); // <-- ¿Esto aparece en la consola?
		var consumoAPI = new ConsumoAPI();
		// var json = consumoAPI.obtenerDatos("https://coffee.alexflipnote.dev/random.json");
		var json = consumoAPI.obtenerDatos("https://gutendex.com/books/");


		System.out.println("Estos son mis libros: "+json);
		System.out.println("************************\n");
		ConvierteDatos conversor = new ConvierteDatos();
		var datos = conversor.obtenerDatos(json, GutendexResponse.class);
		System.out.println("Estos son mis libros: "+ datos.getResults());

	}
}
