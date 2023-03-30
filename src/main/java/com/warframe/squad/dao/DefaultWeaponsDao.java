package com.warframe.squad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.warframe.squad.entity.WeaponType;
import com.warframe.squad.entity.Weapon;
import lombok.extern.slf4j.Slf4j;

@Service
@Component
@Slf4j
public class DefaultWeaponsDao implements WeaponsDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;                          // allows use of named parameters rather than '?' placeholders

  @Override
  public List<Weapon> fetchWeapons(WeaponType weaponType) {
    
    log.debug("DAO: type={}", weaponType);
    
    String sql = "SELECT * FROM weapons WHERE weapon_type = :weapon_type";
    
    Map<String, Object> params = new HashMap<>();                           // Created HashMap to help prevent SQL Injection attacks
    params.put("weapon_type", weaponType.toString());

    return jdbcTemplate.query(sql, params, new RowMapper<>() {              // Returns a list of Weapons objects from SQL query back to the service layer

      @Override
      public Weapon mapRow(ResultSet rs, int rowNum) throws SQLException { // Builds a Weapons object for each row returned by the SQL query
        // @formatter:off
        return Weapon.builder()
            .weapon_pk(rs.getLong("weapon_pk"))
            .weapon_name(rs.getString("weapon_name"))
            .weapon_type(WeaponType.valueOf(rs.getString("weapon_type")))
            .weapon_desc(rs.getString("weapon_desc"))
            .build();
        // @formatter:on
      }});
  }
  
  
  public Weapon saveWeapon(String weaponName, WeaponType weaponType, String weaponDesc) {
    
    log.debug("DAO: weaponName={}, weaponType={}, weaponDesc={}", weaponName, weaponType, weaponDesc);

    SqlParams params = generateInsertSql(weaponName, weaponType, weaponDesc);
    
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);
    
    Long weaponPK = keyHolder.getKey().longValue();
    
    // @formatter:off
    return Weapon.builder()
        .weapon_pk(weaponPK)
        .weapon_name(weaponName)
        .weapon_type(weaponType)
        .weapon_desc(weaponDesc)
        .build();

    // @formatter:on

  }
  
  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
  

  // Assist with creating SQL for PUT / INSERT 
  private SqlParams generateInsertSql(String weaponName, WeaponType weaponType, String weaponDesc) {

    // @formatter:off
    String sql = ""
        + "INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) "
        + "VALUES (:weapon_name, :weapon_type, :weapon_desc)";
    // @formatter:on
    
    SqlParams params = new SqlParams();

    params.sql = sql;
    params.source.addValue("weapon_name", weaponName);
    params.source.addValue("weapon_type", weaponType.toString());
    params.source.addValue("weapon_desc", weaponDesc);
    
    return params;
  }
  
}
