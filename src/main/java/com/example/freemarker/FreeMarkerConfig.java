package com.example.freemarker;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.web.reactive.result.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.reactive.result.view.freemarker.FreeMarkerViewResolver;

@Configuration
public class FreeMarkerConfig {

	private ApplicationContext applicationContext;

	public FreeMarkerConfig(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		// Note this is the reactive version of FreeMarker's configuration, so
		// there is no auto-configuration yet.
		final FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		freeMarkerConfigurer.setPreTemplateLoaders(
				new SpringTemplateLoader(this.applicationContext, "/templates/"));
		return freeMarkerConfigurer;
	}

	/*
	 * ViewResolver for FreeMarker templates executing in NORMAL mode (only mode
	 * available for FreeMarker) No limit to output buffer size, all data fully
	 * resolved in context.
	 */
	@Bean
	public FreeMarkerViewResolver freeMarkerViewResolver() {
		final FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver(
				"", ".ftl");
		freeMarkerViewResolver.setOrder(4);
		// TODO * Apparently no way to specify which views can be handled by
		// this ViewResolver (viewNames property)
		return freeMarkerViewResolver;
	}

}
