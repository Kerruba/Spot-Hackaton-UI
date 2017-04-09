package uk.ac.ebi.biosamples.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import uk.ac.ebi.biosamples.domain.SolrResponse;

import java.util.Properties;

@Controller
public class BaseController {

    private PropertyPlaceholderHelper apiHelper;

    private RestTemplate restTemplate;

    @Value("${ols.api.search.term}")
    public String searchTemplate;

    @Autowired
    public BaseController(PropertyPlaceholderHelper apiHelper, RestTemplate restTemplate) {
        this.apiHelper = apiHelper;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/search")
    public String getOntologies(@RequestParam(defaultValue = "*:*") String searchTerm, Model model) throws NotFoundException {
        // 	replacePlaceholders(String value, Properties properties)
        Properties params = new Properties();
        params.put("search", searchTerm);
        String requestUrl = apiHelper.replacePlaceholders(searchTemplate, params);


        ResponseEntity<SolrResponse> apiResponse = restTemplate.getForEntity(requestUrl, SolrResponse.class);
        if (apiResponse.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("ontologies", apiResponse.getBody().getDocs());
            return "ontologies";
        } else {
            throw new NotFoundException("No results is available for search term " + searchTerm);
        }

        /* FIXME Bit of an hack, but it works too
        ResponseEntity<OntologySummary> apiResponse = restTemplate.getForEntity(requestUrl, OntologySummary.class);
        if (apiResponse.getStatusCode().is2xxSuccessful()) {
            List<OlsSearchDoc> docs = apiResponse.getBody().docs;
            model.addAttribute("ontologies", docs);
            return "ontologies";
        } else {
            throw new NotFoundException("No results is available for search term " + searchTerm);
        }
        */
    }

}
