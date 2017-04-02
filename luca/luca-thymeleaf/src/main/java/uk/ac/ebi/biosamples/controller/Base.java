package uk.ac.ebi.biosamples.controller;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;
import javassist.NotFoundException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Controller
public class Base {

    @RequestMapping("/")
    public String getIndex() {
        return "index";
    }

    @RequestMapping("/help/{page}")
    public String help(@PathVariable String page, Model model, HttpServletResponse response) throws NotFoundException, IOException {
        MutableDataSet options = new MutableDataSet();

        // uncomment to set optional extensions
        options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));

        // uncomment to convert soft-breaks to hard breaks
        //options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        // You can re-use parser and renderer instances
        ClassPathResource classPathResource = new ClassPathResource(String.format("/templates/help/%s.md", page));
        try {
            File helpFile = classPathResource.getFile();
            if (helpFile == null) {
                throw new NotFoundException("We can't find the page you are looking for. Sorry!");
            }
            String fileContent = new String(Files.readAllBytes(Paths.get(helpFile.getAbsolutePath())));
            Node document = parser.parse(fileContent);
            String html = renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
            model.addAttribute("content", html);
            return "help";

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
