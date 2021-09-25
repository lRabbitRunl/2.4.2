package CRUD.config;

import CRUD.Service.RoleService;
import CRUD.model.Role;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.Set;

@Configuration
@EnableWebMvc
@ComponentScan(value = "CRUD", basePackageClasses = RootConfig.class, useDefaultFilters = false, includeFilters = {@ ComponentScan.Filter (Controller.class)})
public class WebConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;
    private final RoleService roleService;

    public WebConfig(ApplicationContext applicationContext, RoleService roleService) {
        this.applicationContext = applicationContext;
        this.roleService = roleService;
    }

    @Bean
    Set<Role> allRoles() {
        Set<Role> temp = roleService.getAllRoles();
        if (temp.isEmpty()) {
            roleService.setRole(new Role("USER"));
            roleService.setRole(new Role("ADMIN"));
        }
        return roleService.getAllRoles();
    }


    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
}
