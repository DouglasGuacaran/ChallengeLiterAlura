package ChallengeLiterAlura.app.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;
import java.util.Map;


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

    private Map<String, String> formats;

    public String getImagenUrl() {
        return (formats != null) ? formats.get("image/jpeg") : null;
    }
}

