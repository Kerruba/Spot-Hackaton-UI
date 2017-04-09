package uk.ac.ebi.biosamples.markdown;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.view.AbstractTemplateView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class MarkdownView extends AbstractTemplateView {

    private MarkdownHandler markdownHandler;

    public MarkdownView() {
        super();
        this.markdownHandler = MarkdownHandler.build();
    }

    public MarkdownView(MarkdownHandler handler) {
        this.markdownHandler = handler;
    }

    @Override
    protected void renderMergedTemplateModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter printWriter = response.getWriter();
        printWriter.append("<html><body>");
        printWriter.append(getHtmlFromMarkdown());
        printWriter.append("</body></html>");
    }

    private String getHtmlFromMarkdown() throws IOException {
//        String templatePath = getUrl();
//        URL templateUrl = MarkdownView.class.getClassLoader().getResource(templatePath);
//        assert templateUrl != null;
//        Path path = Paths.get(templateUrl.toURI());
        File mdFile = new ClassPathResource(getUrl()).getFile();
        return markdownHandler.render(mdFile);
    }
}
