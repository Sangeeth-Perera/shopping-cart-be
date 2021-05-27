package com.system.shoppingcart.mapper;

import com.system.shoppingcart.DTO.Metadata;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class will help to map live detail data related object.
 *
 * @author Sangeeth Perera
 * @Date 2021-05-26
 */
public class SystemConfigMapper implements RowMapper<Metadata> {

    @Override
    public Metadata mapRow(ResultSet resultSet, int i) throws SQLException {
        Metadata metadata = new Metadata();
        metadata.setName(resultSet.getString("name"));
        metadata.setValue(resultSet.getString("value"));
        return metadata;
    }
}
