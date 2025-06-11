package ChallengeLiterAlura.app.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosLibro {

    private Integer id;

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private Integer descargas;

    @JsonAlias("authors")
    private List<AutorDTO> autores;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AutorDTO {
        @JsonAlias("name")
        private String nombre;

        @JsonAlias("birth_year")
        private Integer anioNacimiento;

        @JsonAlias("death_year")
        private Integer anioFallecimiento;

    }


}

