package com.warframe.squad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.warframe.squad.entity.Warframe;
import lombok.extern.slf4j.Slf4j;


@Service
@Component
@Slf4j
public class DefaultWarframeDao implements WarframeDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;                          // allows use of named parameters rather than '?' placeholders

  @Override
  public List<Warframe> fetchWarframes() {

    log.debug("DAO: GET Warframes");
    
    String sql = "SELECT * FROM warframe";

    return jdbcTemplate.query(sql, new RowMapper<>() {              // Returns a list of Operator objects from SQL query back to the service layer

      @Override
      public Warframe mapRow(ResultSet rs, int rowNum) throws SQLException { // Builds an Operator object for each row returned by the SQL query
        // @formatter:off
        return Warframe.builder()
            .warframe_id(rs.getLong("warframe_pk"))
            .operator_id(rs.getLong("operator_fk"))
            .warframe_name(rs.getString("warframe_name"))
            .primary_weapon(rs.getLong("primary_weapon"))
            .secondary_weapon(rs.getLong("secondary_weapon"))
            .melee_weapon(rs.getLong("melee_weapon"))
            .build();
        // @formatter:on
      }});
  }

}
