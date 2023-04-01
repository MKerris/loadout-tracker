package com.warframe.squad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
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
    
    log.debug("DAO newOperator: operatorName={}, focusSchool={}", operatorName, focusSchool);

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

  // generate SQL parameters for newOperator()
  private SqlParams generateInsertSql(String operatorName, FocusSchool focusSchool) {

    // @formatter:off
    String sql = ""
        + "INSERT INTO operator (operator_name, warframe_fk, focus_school) "
        + "VALUES (:operator_name, :warframe_fk, :focus_school)";
    // @formatter:on
    
    SqlParams params = new SqlParams();

    params.sql = sql;
    params.source.addValue("operator_name", operatorName);
    params.source.addValue("warframe_fk", 1);
    params.source.addValue("focus_school", focusSchool.toString());
    
    return params;
    
  }

  @Override
  public void deleteOperator(Long operatorId) {
    
    log.debug("DAO deleteOperator: operatorId={}", operatorId);

    String sql = ""
      + "DELETE FROM operator "
      + "WHERE operator_pk = :operator_pk";
    
    Map<String, Long> params = new HashMap<>();                           // Created HashMap to help prevent SQL Injection attacks
    params.put("operator_pk", operatorId);
       
    jdbcTemplate.update(sql, params);
    
    return;
    
  }

  @Override
  public Operator updateOperator(Long operatorId, Long warframeId, FocusSchool focusSchool) {
    
    log.debug("DAO updateOperator: operatorId={}, warframeId={}, focusSchool={}", operatorId, warframeId, focusSchool);
    
    SqlParams params = generateInsertSql(operatorId, warframeId, focusSchool);
    
    jdbcTemplate.update(params.sql, params.source);
    
    return fetchOperator(operatorId);
  }

  // generate SQL parameters for updateOperator()
  private SqlParams generateInsertSql(Long operatorId, Long warframeId, FocusSchool focusSchool) {
    
    // @formatter:off
    String sql = ""
        + "UPDATE operator SET "
        + "warframe_fk = :warframe_fk, "
        + "focus_school = :focus_school "
        + "WHERE operator_pk = :operator_pk";
    // @formtter:on

    SqlParams params = new SqlParams();

    params.sql = sql;
    params.source.addValue("warframe_fk", warframeId);
    params.source.addValue("focus_school", focusSchool.toString());
    params.source.addValue("operator_pk", operatorId);
    
    return params;

  }
  
  @Override
  public Operator fetchOperator(Long operatorId) {
    //@formatter:off
    String sql = ""
        + "SELECT * FROM operator "
        + "WHERE operator_pk = :operator_pk";
    //@formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("operator_pk", operatorId);
    
    return jdbcTemplate.query(sql, params, new OperatorResultSetExtractor());
  }
  
  class OperatorResultSetExtractor implements ResultSetExtractor<Operator> {
    @Override
    public Operator extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Operator.builder()
          .operator_id(rs.getLong("operator_pk"))
          .operator_name(rs.getString("operator_name"))
          .warframe_id(rs.getLong("warframe_fk"))
          .focus_school(FocusSchool.valueOf(rs.getString("focus_school")))
          .build();
      // @formatter:on

    }
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
  
}
