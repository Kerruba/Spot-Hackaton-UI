package uk.ac.ebi.biosamples.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.ac.ebi.biosamples.MarkdownHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class Base {

    private final MarkdownHandler markdownHandler;

    @Autowired
    public Base(MarkdownHandler markdownHandler) {
        this.markdownHandler = markdownHandler;
    }

    @RequestMapping("/")
    public String getIndex() {
        return "index";
    }

    @RequestMapping("/help/{page}")
    public String help(@PathVariable String page, Model model, HttpServletResponse response) throws NotFoundException, IOException {

        // You can re-use parser and renderer instances
        ClassPathResource classPathResource = new ClassPathResource(String.format("/templates/help/%s.md", page));
        try {
            File helpFile = classPathResource.getFile();
            if (helpFile == null) {
                throw new NotFoundException("We can't find the page you are looking for. Sorry!");
            }
            String html = markdownHandler.render(helpFile);
            model.addAttribute("content", html);
            return "help";

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @RequestMapping("/help/{page}/md")
    public String mdHelp(@PathVariable String page) {
        return "help/" + page;
    }

}
