package com.warframe.squad.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warframe.squad.dao.WeaponsDao;
import com.warframe.squad.entity.WeaponType;
import com.warframe.squad.entity.Weapon;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultWeaponsService implements WeaponsService {
  
  @Autowired
  private WeaponsDao weaponsDao;

  @Transactional(readOnly = true)
  @Override
  public List<Weapon> fetchWeapons(WeaponType weaponType) {
    
    log.info("The fetchWeapons method was callled with type={}", weaponType);
    
    List<Weapon> weapons = weaponsDao.fetchWeapons(weaponType);
    
    if(weapons.isEmpty()) {
      String msg = String.format("No weapons were found with type of %s", weaponType);
      throw new NoSuchElementException(msg);
    }
    
    return weapons;
  }

  @Transactional
  @Override
  public Weapon saveWeapon(String weaponName, WeaponType weaponType, String weaponDesc) {
    
    // Break object into three variables to write values to DB
    
    return weaponsDao.saveWeapon(weaponName, weaponType, weaponDesc);
  }

}
