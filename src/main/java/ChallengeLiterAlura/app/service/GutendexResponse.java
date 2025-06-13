package ChallengeLiterAlura.app.service;

import ChallengeLiterAlura.app.dto.DatosLibro;
import ChallengeLiterAlura.app.model.Book;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponse {
    private List<DatosLibro> results;
    private String next;

    public List<DatosLibro> getResults() {
        return results;
    }

    public void setResults(List<DatosLibro> results) {
        this.results = results;
    }

}