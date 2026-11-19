package com.tesla.platform.u20241g031.shared.infrastructure.i18n.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

/**
 * Internationalization configuration resolving request locales from the Accept-Language header
 * with English as default and Spanish (ES, ES-PE) support.
 *
 * @author Piero Francisco Montes Chang
 */
@Configuration
public class LocaleConfiguration {

    /**
     * Configures an {@link AcceptHeaderLocaleResolver} defaulting to English.
     *
     * @return configured {@link LocaleResolver}
     */
    @Bean
    public LocaleResolver localeResolver() {
        var resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.ENGLISH);
        resolver.setSupportedLocales(List.of(
                Locale.ENGLISH,
                Locale.US,
                Locale.forLanguageTag("es"),
                Locale.forLanguageTag("es-PE")
        ));
        return resolver;
    }
}