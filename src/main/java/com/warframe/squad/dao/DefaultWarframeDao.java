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

    return jdbcTemplate.query(sql, new RowMapper<>() {

      @Override
      public Warframe mapRow(ResultSet rs, int rowNum) throws SQLException {
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

  @Override
  public Warframe updateWarframe(Long operatorId, String warframeName, Long primaryWeapon,
      Long secondaryWeapon, Long meleeWeapon) {
    
    log.debug("DAO updateWarframe: operatorId={}, warframeName={}, primaryWeapon={}, secondaryWeapon={}, meleeWeapon={}",
        operatorId, warframeName, primaryWeapon, secondaryWeapon, meleeWeapon);
    
    Warframe warframe = fetchOperatorWarframe(operatorId);                          // Fetch existing Warframe loadout for Operator_fk
    
    log.debug("DAO updateWarframe: existing Warframe: {}", warframe);               // Log returned values in console 

    warframe.setWarframe_name(warframeName);                                        // Replace existing values with new values
    warframe.setPrimary_weapon(primaryWeapon);
    warframe.setSecondary_weapon(secondaryWeapon);
    warframe.setMelee_weapon(meleeWeapon);
    
    log.debug("DAO updateWarframe: new Warframe: {}", warframe);                    // Log replaced values in console
    
    SqlParams params = generateInsertSql(warframe);                                 // Generate sql with new Warframe values
    
    jdbcTemplate.update(params.sql, params.source);
    
    return null;
  }

  
  // Used with updateWarframe() to generate SQL statement
  private SqlParams generateInsertSql(Warframe warframe) {
    // @formatter:off
    String sql = ""
        + "UPDATE warframe SET "
        + "warframe_name = :warframe_name, "
        + "primary_weapon = :primary_weapon, "
        + "secondary_weapon = :secondary_weapon, " 
        + "melee_weapon = :melee_weapon "
        + "WHERE warframe_pk = :warframe_pk";
    // @formatter:on
    
    SqlParams params = new SqlParams();
    
    params.sql = sql;
    params.source.addValue("warframe_name", warframe.getWarframe_name());
    params.source.addValue("primary_weapon", warframe.getPrimary_weapon());
    params.source.addValue("secondary_weapon", warframe.getSecondary_weapon());
    params.source.addValue("melee_weapon", warframe.getMelee_weapon());
    params.source.addValue("warframe_pk", warframe.getWarframe_id());
    
    return params;
  }

 
  // Used with updateWarframe()
  @Override
  public Warframe fetchOperatorWarframe(Long operatorId) {
    //@formatter:off
    String sql = ""
        + "SELECT * FROM warframe "
        + "WHERE operator_fk = :operator_fk";
    //@formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("operator_fk", operatorId);
    
    return jdbcTemplate.query(sql, params, new WarframeResultSetExtractor());
  }
  
  // Used with fetchOperatorWarframe()
  class WarframeResultSetExtractor implements ResultSetExtractor<Warframe> {
    @Override
    public Warframe extractData(ResultSet rs) throws SQLException {
      rs.next();

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
    }
  }
  
  
  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
  
  
}
