package ChallengeLiterAlura.app.service;

import ChallengeLiterAlura.app.dto.DatosLibro;
import ChallengeLiterAlura.app.model.Book;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponse {
    private List<DatosLibro> results;

    public List<DatosLibro> getResults() {
        return results;
    }

    public void setResults(List<DatosLibro> results) {
        this.results = results;
    }
}