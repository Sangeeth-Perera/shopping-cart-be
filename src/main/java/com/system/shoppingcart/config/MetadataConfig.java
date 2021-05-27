package com.system.shoppingcart.config;

import com.system.shoppingcart.DTO.Metadata;
import com.system.shoppingcart.mapper.SystemConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.HashMap;
import java.util.List;

/**
 * This class is used to read System configurations.
 *
 * @author Sangeeth Perera
 * @version  2021.05.26
 */
@Slf4j
public class MetadataConfig {

    private MetadataConfig() {}

    /**
     * This method will help to read metadata from database and save it to the singleton.
     *
     * @param jdbcTemplate JDBC Template.
     */
    public static void configureMetadata(JdbcTemplate jdbcTemplate) throws Exception {
        String sql = "SELECT * FROM meta_data;";

        try {
            List<Metadata> metadataList = jdbcTemplate.query(sql, new SystemConfigMapper());
            HashMap<String, String> metadataHashMap = Metadata.getInstance();
            for(Metadata metadata : metadataList)
            {
                if(metadataHashMap.containsKey(metadata.getName())){
                    metadataHashMap.replace(metadata.getName(), metadata.getValue());
                }
                else {
                    metadataHashMap.put(metadata.getName(), metadata.getValue());
                }
            }
            Metadata.configureMetadata(metadataHashMap);
        }catch (EmptyResultDataAccessException e){
            log.warn("No meta data records found in database.");
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * This method will help to write the given metadata to database.
     *
     * @param jdbcTemplate JDBC Template.
     * @param metadataList Metadata Object List.
     */
    public static void writeMetadata(JdbcTemplate jdbcTemplate, List<Metadata> metadataList) throws Exception {
        String sql = null;
        MapSqlParameterSource parameterSource = null;
        for (Metadata metadata : metadataList) {
            parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("name", metadata.getName());
            parameterSource.addValue("value", metadata.getValue());
            try {
                if(isRecordExists(jdbcTemplate, metadata.getName())) {
                    sql = "UPDATE metadata SET value = :value WHERE name = :name;";
                }
                else {
                    sql = "INSERT INTO meta_data (name, value) VALUES(:name, :value);";
                }
                jdbcTemplate.update(sql, parameterSource);
            } catch (Exception e) {
                throw new Exception();
            }
        }
    }

    /**
     * This method will help to check whether the given record in the database.
     *
     * @param jdbcTemplate  JDBC Template.
     * @param name Metadata Name.
     *
     * @return @{@link boolean}
     */
    private static boolean isRecordExists(JdbcTemplate jdbcTemplate, String name) {
        String sql = "SELECT COUNT(*) FROM metadata WHERE name = ?;";
        try {
            int count = jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{ name });
            return count > 0;
        } catch (Exception e) {
            log.error("isRecordExists method failed");
            return false;
        }
    }
}
