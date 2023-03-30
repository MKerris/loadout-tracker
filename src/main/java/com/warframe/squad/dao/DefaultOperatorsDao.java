package com.warframe.squad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.warframe.squad.entity.FocusSchool;
import com.warframe.squad.entity.Operator;
import lombok.extern.slf4j.Slf4j;

@Service
@Component
@Slf4j
public class DefaultOperatorsDao implements OperatorsDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;                          // allows use of named parameters rather than '?' placeholders
  
  @Override
  public List<Operator> fetchOperators() {

    log.debug("DAO: GET Operators");
    
    String sql = "SELECT * FROM operator";

    return jdbcTemplate.query(sql, new RowMapper<>() {              // Returns a list of Operator objects from SQL query back to the service layer

      @Override
      public Operator mapRow(ResultSet rs, int rowNum) throws SQLException { // Builds an Operator object for each row returned by the SQL query
        // @formatter:off
        return Operator.builder()
            .operator_id(rs.getLong("operator_pk"))
            .operator_name(rs.getString("operator_name"))
            .warframe_id(rs.getLong("warframe_fk"))
            .focus_school(FocusSchool.valueOf(rs.getString("focus_school")))
            .build();
        // @formatter:on
      }});
  }

}
