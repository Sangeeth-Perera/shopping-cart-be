package com.system.shoppingcart;

import com.system.shoppingcart.DTO.Metadata;
import com.system.shoppingcart.config.MetadataConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Slf4j
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
        log.info("horseshoe-carton-count {}" , Metadata.getInstance().get("horseshoe-carton-count"));
        log.info("penguin-ears-carton-count {}", Metadata.getInstance().get("penguin-ears-carton-count"));
        log.info("single-unit-rate {}", Metadata.getInstance().get("single-unit-rate"));
        log.info("max-cartons-discount {}", Metadata.getInstance().get("max-cartons-discount"));
        log.info("cartons-discount-percentage {}", Metadata.getInstance().get("cartons-discount-percentage"));
    }

}
