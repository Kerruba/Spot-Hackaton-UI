package uk.ac.ebi.biosamples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.ac.ebi.biosamples.markdown.MarkdownHandler;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/help")
public class HelpController {

    private final MarkdownHandler markdownHandler;

    @Autowired
    public HelpController(MarkdownHandler markdownHandler) {
        this.markdownHandler = markdownHandler;
    }

    @GetMapping("/")
    public String index(Model model) throws IOException {

        String md = getMarkdown("/templates/help/index.md");
        model.addAttribute("content", md);
        return "help";
    }

    @GetMapping(value = "/{page}")
    public String help(@PathVariable String page, Model model) throws IOException {

        // You can re-use parser and renderer instances
        String md = getMarkdown(String.format("/templates/help/%s.md", page));
        model.addAttribute("content", md);
        return "help";
    }

    /**
     * Return simple markdown version of a page (not in thymeleaf)
     */
    @GetMapping(value = "{page}/md")
    public String mdHelp(@PathVariable String page) {
        return "help/" + page;
    }



    private String getMarkdown(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        File helpFile = resource.getFile();
        return markdownHandler.render(helpFile);
    }
}
