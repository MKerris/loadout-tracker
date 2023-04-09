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
import com.warframe.squad.entity.FocusSchool;
import com.warframe.squad.entity.Operator;
import com.warframe.squad.entity.Warframe;
import com.warframe.squad.entity.Weapon;
import com.warframe.squad.entity.WeaponType;
import lombok.extern.slf4j.Slf4j;


@Service
@Component
@Slf4j
public class DefaultWarframeDao implements WarframeDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

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
  public Warframe updateWarframeName(Long operatorId, String warframeName) {
    
    log.debug("DAO: updateWarframeName: operatorId={}, warframeName={}", operatorId, warframeName);
    
    SqlParams params = generateInsertSqlUpdateWarframeName(operatorId, warframeName);
    
    jdbcTemplate.update(params.sql, params.source);
    
    return fetchOperatorWarframe(operatorId);                                                           // Return updated Warframe details in Warframe object

  }

  
  private SqlParams generateInsertSqlUpdateWarframeName(Long operatorId, String warframeName) {

    // @formatter:off
    String sql = ""
        + "UPDATE warframe SET "
        + "warframe_name = :warframe_name "
        + "WHERE operator_fk = :operator_fk";
    // @formatter:on
    
    SqlParams params = new SqlParams();
    
    params.sql = sql;
    params.source.addValue("operator_fk", operatorId);
    params.source.addValue("warframe_name", warframeName);
    
    return params;  
    
  }


  @Override
  public Warframe updateWarframePrimary(Long operatorId, Long primaryWeaponPk) {

    log.debug("DAO: updateWarframePrimary: operatorId={}, primaryWeaponPk={}", operatorId, primaryWeaponPk);
    
    Warframe warframe = fetchOperatorWarframe(operatorId);                                              // Fetch existing Warframe loadout for Operator_fk
    
    SqlParams params = generateInsertSqlUpdateWarframePrimary(warframe.getWarframe_id(), primaryWeaponPk);
    
    jdbcTemplate.update(params.sql, params.source);
    
    updateWarframeTablePrimary(warframe.getWarframe_id());
    
    return fetchOperatorWarframe(operatorId);                                                           // Return updated Warframe details in Warframe object

  }

 
  
  private SqlParams generateInsertSqlUpdateWarframePrimary(Long warframeId, Long primaryWeaponPk) {

    // @formatter:off
    String sql = ""
        + "UPDATE warframe_weapon SET "
        + "weapon_fk = :weapon_fk "
        + "WHERE warframe_fk = :warframe_fk AND weapon_type = 'PRIMARY'";
    // @formatter:on
    
    SqlParams params = new SqlParams();
    
    params.sql = sql;
    params.source.addValue("warframe_fk", warframeId);
    params.source.addValue("weapon_fk", primaryWeaponPk);
    
    return params;  
    
  }
  
  
  @Override
  public Warframe updateWarframeSecondary(Long operatorId, Long secondaryWeaponPk) {

    log.debug("DAO: updateWarframeSecondary: operatorId={}, secondaryWeaponPk={}", operatorId, secondaryWeaponPk);
    
    Warframe warframe = fetchOperatorWarframe(operatorId);                                              // Fetch existing Warframe loadout for Operator_fk
    
    SqlParams params = generateInsertSqlUpdateWarframeSecondary(warframe.getWarframe_id(), secondaryWeaponPk);
    
    jdbcTemplate.update(params.sql, params.source);
    
    updateWarframeTableSecondary(warframe.getWarframe_id());
    
    return fetchOperatorWarframe(operatorId);                                                           // Return updated Warframe details in Warframe object

  }

  
  private SqlParams generateInsertSqlUpdateWarframeSecondary(Long warframeId, Long secondaryWeaponPk) {

    // @formatter:off
    String sql = ""
        + "UPDATE warframe_weapon SET "
        + "weapon_fk = :weapon_fk "
        + "WHERE warframe_fk = :warframe_fk AND weapon_type = 'SECONDARY'";
    // @formatter:on
    
    SqlParams params = new SqlParams();
    
    params.sql = sql;
    params.source.addValue("warframe_fk", warframeId);
    params.source.addValue("weapon_fk", secondaryWeaponPk);
    
    return params;  
    
  }
  
  
  public Warframe updateWarframeMelee(Long operatorId, Long meleeWeaponPk) {

    log.debug("DAO: updateWarframeMelee: operatorId={}, meleeWeaponPk={}", operatorId, meleeWeaponPk);
    
    Warframe warframe = fetchOperatorWarframe(operatorId);                                              // Fetch existing Warframe loadout for Operator_fk
    
    SqlParams params = generateInsertSqlUpdateWarframeMelee(warframe.getWarframe_id(), meleeWeaponPk);
    
    jdbcTemplate.update(params.sql, params.source);
    
    updateWarframeTableMelee(warframe.getWarframe_id());
    
    return fetchOperatorWarframe(operatorId);                                                           // Return updated Warframe details in Warframe object

  }

  
  private SqlParams generateInsertSqlUpdateWarframeMelee(Long warframeId, Long meleeWeaponPk) {

    // @formatter:off
    String sql = ""
        + "UPDATE warframe_weapon SET "
        + "weapon_fk = :weapon_fk "
        + "WHERE warframe_fk = :warframe_fk AND weapon_type = 'MELEE'";
    // @formatter:on
    
    SqlParams params = new SqlParams();
    
    params.sql = sql;
    params.source.addValue("warframe_fk", warframeId);
    params.source.addValue("weapon_fk", meleeWeaponPk);
    
    return params;  
    
  }


/*  

  //    Loadout loadout = fetchWarframeLoadout(warframe.getWarframe_id());                                  // Fetch existing weapons (aka: Loadout) for selected warframe_id
  
  private Loadout fetchWarframeLoadout(Long warframe_id) {
    // @formatter:off
    String sql = ""
        + "SELECT ww.warframe_fk, ww.weapon_fk, w.weapon_name, w.weapon_type, w.weapon_desc "           // SQL calls records from table warframe_weapon 
        + "FROM warframe_weapon AS ww "                                                                 // for a given warframe_id, which is unique per Operator
        + "LEFT JOIN weapons AS w ON ww.weapon_fk = w.weapon_pk "                                       // The Warframe should have three records in warframe_weapon
        + "WHERE ww.warframe_fk = :ww.warframe_fk";                                                     // One of each weapon_type (PRIMARY, SECONDARY, and MELEE)
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();                                                       // Prepared statement to protect against SQL injection attack
    params.put("ww.warframe_fk", warframe_id);
    
    return jdbcTemplate.query(sql, params, new LoadoutResultSetExtractor());
    
  }

  // Used with fetchWarframeLoadout()
  class LoadoutResultSetExtractor implements ResultSetExtractor<Loadout> {
    @Override
    public Loadout extractData(ResultSet rs) throws SQLException {
      rs.next();

      Loadout loadout = new Loadout();                                                                  // Loadout object contains warframe_id and three weapons
      
      loadout.setWarframeFk(rs.getLong("warframe_fk"));                                                 // Set the warframe_id as the PK in the Loadout object

      do  {
        if(rs.getString("weapon_type").equals("PRIMARY")) {                                             // Set PK and weapon_name if the weapon_type is PRIMARY
          loadout.setPrimaryWeaponPk(rs.getLong("weapon_fk"));
          loadout.setPrimaryWeaponName(rs.getString("weapon_name"));
        }
        if(rs.getString("weapon_type").equals("SECONDARY")) {                                           // Set PK and weapon_name if the weapon_type is SECONDARY
          loadout.setSecondaryWeaponPk(rs.getLong("weapon_fk"));
          loadout.setSecondaryWeaponName(rs.getString("weapon_name"));
        }
        if(rs.getString("weapon_type").equals("MELEE")) {                                               // Set PK and weapon_name if the weapon_type is MELEE
          loadout.setMeleeWeaponPk(rs.getLong("weapon_fk"));
          loadout.setMeleeWeaponName(rs.getString("weapon_name"));
        }
      
      } while (rs.next());
      
      return loadout;

    }
  }
*/
   
  @Override
  public Warframe fetchOperatorWarframe(Long operatorId) {
    //@formatter:off
    String sql = ""
        + "SELECT * FROM warframe "                                                                     // Get details from warframe table for a given Operator
        + "WHERE operator_fk = :operator_fk";                                                           // The warframe_fk will be used as the warframe_id 
    //@formatter:on                                                                                     // to pull the weapons associated with that Warframe

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
          .warframe_id(rs.getLong("warframe_pk"))                                                       // warframe_pk will be used as warframe_id elsewhere
          .operator_id(rs.getLong("operator_fk"))                                                       // Index of Operator from operator table
          .warframe_name(rs.getString("warframe_name"))                                                 // String for name of Warframe
          .primary_weapon(rs.getLong("primary_weapon"))                                                 // PK of weapon with weapon_type of PRIMARY in table weapons
          .secondary_weapon(rs.getLong("secondary_weapon"))                                             // PK of weapon with weapon_type of SECONDARY in table weapons
          .melee_weapon(rs.getLong("melee_weapon"))                                                     // PK of weapon with weapon_type of MELEE in table weapons
          .build();
      // @formatter:on  
    }
  }
  
  
  public void updateWarframeTablePrimary(Long warframeId) {

    //@formatter:off
    String sql = ""
        + "UPDATE warframe AS w "
        + "LEFT JOIN warframe_weapon AS ww "
        + "ON w.warframe_pk = ww.warframe_fk "
        + "AND ww.weapon_type = 'PRIMARY' "
        + "SET "
        + "w.primary_weapon = ww.weapon_fk "
        + "WHERE w.warframe_pk = :w.warframe_pk";
    
    Map<String, Object> params = new HashMap<>();
    params.put("w.warframe_pk", warframeId);
    
    jdbcTemplate.update(sql, params);
    
    //@formatter:on
  }
  
  public void updateWarframeTableSecondary(Long warframeId) {

    //@formatter:off
    String sql = ""
        + "UPDATE warframe AS w "
        + "LEFT JOIN warframe_weapon AS ww "
        + "ON w.warframe_pk = ww.warframe_fk "
        + "AND ww.weapon_type = 'SECONDARY' "
        + "SET "
        + "w.secondary_weapon = ww.weapon_fk "
        + "WHERE w.warframe_pk = :w.warframe_pk";
    
    Map<String, Object> params = new HashMap<>();
    params.put("w.warframe_pk", warframeId);
    
    jdbcTemplate.update(sql, params);
    
    //@formatter:on
  }
  
  public void updateWarframeTableMelee(Long warframeId) {

    //@formatter:off
    String sql = ""
        + "UPDATE warframe AS w "
        + "LEFT JOIN warframe_weapon AS ww "
        + "ON w.warframe_pk = ww.warframe_fk "
        + "AND ww.weapon_type = 'MELEE' "
        + "SET "
        + "w.melee_weapon = ww.weapon_fk "
        + "WHERE w.warframe_pk = :w.warframe_pk";
    
    Map<String, Object> params = new HashMap<>();
    params.put("w.warframe_pk", warframeId);
    
    jdbcTemplate.update(sql, params);
    
    //@formatter:on
  }
  
  public List<Operator> checkOperator(Long operatorId) {

    // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM operator " 
        + "WHERE operator_pk = :operator_pk";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("operator_pk", operatorId);

    return jdbcTemplate.query(sql, params, new RowMapper<>() {

      @Override
      public Operator mapRow(ResultSet rs, int rowNum) throws SQLException {
        // @formatter:off
        return Operator.builder()
            .operator_id(rs.getLong("operator_pk"))
            .operator_name(rs.getString("operator_name"))
            .focus_school(FocusSchool.valueOf(rs.getString("focus_school")))
            .warframe_id(rs.getLong("warframe_fk"))
            .build();
        // @formatter:on
      }});
    
  }
  
  public List<Weapon> checkWeapon(Long weaponId) {

    // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM weapons " 
        + "WHERE weapon_pk = :weapon_pk";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("weapon_pk", weaponId);

    return jdbcTemplate.query(sql, params, new RowMapper<>() {

      @Override
      public Weapon mapRow(ResultSet rs, int rowNum) throws SQLException {
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


  public List<Weapon> checkWeaponAndType(Long weaponId, WeaponType weaponType) {

    // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM weapons " 
        + "WHERE weapon_pk = :weapon_pk AND weapon_type = :weapon_type";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("weapon_pk", weaponId);
    params.put("weapon_type", weaponType.toString());

    return jdbcTemplate.query(sql, params, new RowMapper<>() {

      @Override
      public Weapon mapRow(ResultSet rs, int rowNum) throws SQLException {
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


  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }



}
