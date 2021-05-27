package com.system.shoppingcart.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * This class will represents the entire system config object.
 *
 * @author Sangeeth Perera
 * @version  2021.05.26
 */
@NoArgsConstructor
@ToString
public class Metadata {

    private static HashMap<String, String> instance;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String value;

    public static synchronized HashMap<String, String> getInstance() {
        if (instance == null) {
            instance = new LinkedHashMap<>();
        }
        return instance;
    }

    public static void configureMetadata(HashMap<String, String> configs) {
        instance = configs;
    }

}
