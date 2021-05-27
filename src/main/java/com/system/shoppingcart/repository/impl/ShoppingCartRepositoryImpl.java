package com.system.shoppingcart.repository.impl;

import com.system.shoppingcart.repository.ShoppingCartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.JdbcRowSet;
import java.security.spec.NamedParameterSpec;
@Slf4j
@Repository
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {

    NamedParameterJdbcTemplate readJdbcTemplate;
    public ShoppingCartRepositoryImpl(NamedParameterJdbcTemplate readJdbcTemplate){
        this.readJdbcTemplate = readJdbcTemplate;
    }
    /**
     * {@inheritDoc}
     */

    @Override
    public Double getItemPrice(String productName) throws Exception {
        String sql = "SELECT item_price FROM items WHERE item_code = :itemCode;";
        try {
            return readJdbcTemplate.queryForObject(sql, new MapSqlParameterSource().addValue("itemCode", productName), Double.class);
        }catch (EmptyResultDataAccessException e){
            log.info("No record found in database");
            return null;
        } catch (Exception e) {
            throw new Exception();
        }
    }

}
