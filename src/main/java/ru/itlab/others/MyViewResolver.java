package ru.itlab.others;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;

public class MyViewResolver extends InternalResourceViewResolver {
    public final String STATUS_PREFIX = "status:";

    @Override
    protected View createView(String viewName, Locale locale) throws Exception {
        View view = super.createView(viewName, locale);
        return view.getContentType().startsWith(STATUS_PREFIX) ? view : getView(viewName, locale);
    }

    private View getView(String viewName, Locale locale) throws Exception {
        if (!canHandle(viewName, locale)) {
            return null;
        }
        if (viewName.startsWith(STATUS_PREFIX)) {
            logger.info(viewName);
            String statusCode = viewName.substring(STATUS_PREFIX.length());
            int code = Integer.parseInt(statusCode);
            switch (code) {
                case 404:
                    return super.createView("404", locale);
                case 403:
                    return super.createView("403", locale);
                case 500:
                    return super.createView("500", locale);
            }
            logger.info("Error doesn't work");
        }

        return null;
    }

}
