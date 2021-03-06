package utils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class TemplateEngine {

    private final Configuration config;

    public TemplateEngine(String rootLocation) throws IOException {
        this.config = new Configuration(Configuration.VERSION_2_3_28){{
            setDirectoryForTemplateLoading(new File(rootLocation));
            setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
            setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            setLogTemplateExceptions(false);
            setWrapUncheckedExceptions(true);
        }};
    }

    public static TemplateEngine folder(String rootLocation) throws IOException {
        return new TemplateEngine(rootLocation);
    }

    public void render(String templateFile, HashMap<String, Object>data, HttpServletResponse response){
        response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        try(PrintWriter w = response.getWriter()) {
            config.getTemplate(templateFile).process(data, w);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
