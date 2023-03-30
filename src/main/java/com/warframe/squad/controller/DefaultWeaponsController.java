package com.warframe.squad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.warframe.squad.entity.WeaponType;
import com.warframe.squad.entity.Weapon;
import com.warframe.squad.service.WeaponsService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultWeaponsController implements WeaponsController {
  
  @Autowired
  private WeaponsService weaponsService;
  
  @Override
  public List<Weapon> fetchWeapons(WeaponType weaponType) {
    
    log.debug("weaponType={}", weaponType);
    
    return weaponsService.fetchWeapons(weaponType);
  }
  


  public Weapon saveWeapon(String weaponName, WeaponType weaponType, String weaponDesc) {
    
    log.debug("weapon_name={}, weapon_type={}, weapon_desc={}", weaponName, weaponType, weaponDesc);

    return weaponsService.saveWeapon(weaponName, weaponType, weaponDesc);
    
  }

}
