package uk.ac.ebi.biosamples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Properties;

import static java.util.Arrays.asList;

@Controller
public class BaseController {

    final
    PropertyPlaceholderHelper apiHelper;

    @Value("${ols.api.search.term}")
    public String searchTemplate;

    @Autowired
    public BaseController(PropertyPlaceholderHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    @GetMapping("/search")
    public String getOntologies(@RequestParam(defaultValue = "*:*") String searchTerm, Model model) {
        // 	replacePlaceholders(String value, Properties properties)
        Properties params = new Properties();
        params.put("search", searchTerm);
        String requestUrl = apiHelper.replacePlaceholders(searchTemplate, params);



        model.addAttribute("ontologies", asList("test1", "test2", "test3"));
        return "ontologies";
    }

}
