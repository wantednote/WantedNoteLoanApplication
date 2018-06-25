package com.wn.loanapp.config;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration 
//@ComponentScan(basePackages="com.wn.loanapp.*") 
@EnableWebMvc
@SpringBootApplication
//@EntityScan(basePackages = "org.app.genesis.*.model")
public class AppConfig /*extends SpringBootServletInitializer*/ implements WebMvcConfigurer {
    
    public static final String RESOURCE_LOCATIONS = "";
    
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	   // registry.addViewController("/login").setViewName("login");
	   // registry.addViewController("/error").setViewName("errorPage");
	}
	
    @Bean
	public UrlBasedViewResolver tilesViewResolver() {

		UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		tilesViewResolver.setOrder(2);
		return tilesViewResolver;
	}
    
    /*@Bean
    public ResourceBundleViewResolver pdfViewResolver(){
    	ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();
    	resolver.setBasename("pdfViews");
    	resolver.setOrder(1);
    	return resolver;
    }*/
    
    /*@Bean
    public ViewResolver getCsvViewResolver(){
        ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();
        resolver.setBasename("csvViews");
        resolver.setOrder(1);
        return resolver;
    }*/
    
    @Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
    
    @Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tconf = new TilesConfigurer();
		tconf.setDefinitions(new String[] {"/WEB-INF/tiles/tiles.xml" });
		tconf.setCheckRefresh(true);
		return tconf;
	}
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	System.out.println("inside addResourceHandlers");
            registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
            registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");
       
            registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true);
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**");
    }
    
    /*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AppConfig.class);
	}*/
    
    /*public static void main(String[] args) {
		SpringApplication.run(AppConfig.class, args);
	}*/
} 