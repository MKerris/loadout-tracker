package com.warframe.squad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.warframe.squad.entity.Operator;
import com.warframe.squad.service.OperatorsService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultOperatorsController implements OperatorsController {

  @Autowired
  private OperatorsService warframeOperatorsService;
  
  @Override
  public List<Operator> fetchOperators() {

    log.debug("Controller: GET Operators");
    
    return warframeOperatorsService.fetchOperators();
  }

  
/*
  public Weapon saveWeapon(String weaponName, WeaponType weaponType, String weaponDesc) {
    
    log.debug("weapon_name={}, weapon_type={}, weapon_desc={}", weaponName, weaponType, weaponDesc);

    return warframeWeaponsService.saveWeapon(weaponName, weaponType, weaponDesc);
    
  }

 */

}