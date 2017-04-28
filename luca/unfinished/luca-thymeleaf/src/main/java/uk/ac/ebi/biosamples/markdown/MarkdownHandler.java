package uk.ac.ebi.biosamples.markdown;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;



public class MarkdownHandler {

    private Parser parser;
    private HtmlRenderer renderer;

    private MarkdownHandler(MutableDataSet options) {

        // uncomment to set optional extensions
//        options.set(EXTENSIONS,
//                Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));

        // uncomment to convert soft-breaks to hard breaks
        //options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");

        parser = Parser.builder(options).build();
        renderer = HtmlRenderer.builder(options).build();
    }

    public String render(File file) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        return this.render(fileContent);
    }

    public String render(String md) {
        Node document = parser.parse(md);
        return renderer.render(document);
    }

    public static MarkdownHandler build() {
        MutableDataSet options = new MutableDataSet();
        options.set(Parser.EXTENSIONS,
                Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));
        return new MarkdownHandler(options);
    }

    public static MarkdownHandler build(MutableDataSet options) {
        return new MarkdownHandler(options);
    }
}
