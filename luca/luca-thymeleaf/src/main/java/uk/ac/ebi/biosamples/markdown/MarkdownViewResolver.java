package uk.ac.ebi.biosamples.markdown;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

import java.io.IOException;
import java.util.Locale;

public class MarkdownViewResolver extends AbstractTemplateViewResolver {

    public MarkdownViewResolver() {
        setViewClass(requiredViewClass());
    }

    @Override
    protected Class<?> requiredViewClass() {
        return MarkdownView.class;
    }

    @Override
    protected boolean canHandle(String viewName, Locale locale) {
        String templatePath = getPrefix() + viewName + getSuffix();
        try {
            new ClassPathResource(templatePath).getFile();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
