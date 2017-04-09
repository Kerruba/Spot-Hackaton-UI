package uk.ac.ebi.biosamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import uk.ac.ebi.biosamples.markdown.MarkdownHandler;
import uk.ac.ebi.biosamples.markdown.MarkdownViewResolver;

@SpringBootApplication
public class LucaThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(LucaThymeleafApplication.class, args);
	}

	@Bean
	public MarkdownHandler getMarkdownHandler() {
		return MarkdownHandler.build();
	}

	@Bean
	public ViewResolver getViewResolver(){
		MarkdownViewResolver resolver = new MarkdownViewResolver();
		resolver.setSuffix(".md");
		resolver.setOrder(0);
		return resolver;
	}


}
