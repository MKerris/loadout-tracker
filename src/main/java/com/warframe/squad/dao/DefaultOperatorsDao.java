package com.warframe.squad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

  @Override
  public Operator newOperator(String operatorName, FocusSchool focusSchool) {
    
    log.debug("DAO: operatorName={}, focusSchool={}", operatorName, focusSchool);

    SqlParams params = generateInsertSql(operatorName, focusSchool);
    
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);
    
    Long operatorPK = keyHolder.getKey().longValue();
    
    // @formatter:off
    return Operator.builder()
        .operator_id(operatorPK)
        .operator_name(operatorName)
        .warframe_id((long) 1)
        .focus_school(focusSchool)
        .build();

    // @formatter:on

  }
  
  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
  

  // Assist with creating SQL for PUT / INSERT 
  private SqlParams generateInsertSql(String operatorName, FocusSchool focusSchool) {

    // @formatter:off
    String sql = ""
        + "INSERT INTO operator (operator_name, focus_school) "
        + "VALUES (:operator_name, :focus_school)";
    // @formatter:on
    
    SqlParams params = new SqlParams();

    params.sql = sql;
    params.source.addValue("operator_name", operatorName);
    params.source.addValue("focus_school", focusSchool.toString());
    
    return params;
    
  }

}
