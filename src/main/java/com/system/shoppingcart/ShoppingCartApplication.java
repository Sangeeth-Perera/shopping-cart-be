package com.system.shoppingcart;

import com.system.shoppingcart.DTO.Metadata;
import com.system.shoppingcart.config.MetadataConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ShoppingCartApplication {

    private final JdbcTemplate jdbcTemplate;

    public ShoppingCartApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
    }

    @PostConstruct
    public void postConstruct() throws Exception {
        MetadataConfig.configureMetadata(jdbcTemplate);
        System.out.println(Metadata.getInstance().get("horseshoe-carton-count"));
        System.out.println(Metadata.getInstance().get("penguin-ears-carton-count"));
        System.out.println(Metadata.getInstance().get("single-unit-rate"));
        System.out.println(Metadata.getInstance().get("max-cartons-discount"));
        System.out.println(Metadata.getInstance().get("cartons-discount-percentage"));
    }

}
