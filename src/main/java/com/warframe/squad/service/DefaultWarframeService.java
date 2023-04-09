package com.warframe.squad.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warframe.squad.dao.WarframeDao;
import com.warframe.squad.entity.Operator;
import com.warframe.squad.entity.Warframe;
import com.warframe.squad.entity.Weapon;
import com.warframe.squad.entity.WeaponType;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultWarframeService implements WarframeService {

  @Autowired
  private WarframeDao warframeDao;
  
  @Transactional(readOnly = true)
  @Override
  public List<Warframe> fetchWarframes() {
    
    log.info("Service: GET Warframes");
    
    List<Warframe> warframes = warframeDao.fetchWarframes();

    return warframes;
  }

  
  @Override
  public Warframe updateWarframeName(Long operatorId, String warframeName) {
    
    try {
      checkOperator(operatorId);

      return warframeDao.updateWarframeName(operatorId, warframeName);

    } finally {}
    
  }

  @Override
  public Warframe updateWarframePrimary(Long operatorId, Long primaryWeaponPk) {

    try {
      checkOperator(operatorId);
      checkWeaponAndType(primaryWeaponPk, WeaponType.PRIMARY);

      return warframeDao.updateWarframePrimary(operatorId, primaryWeaponPk);

    } finally {}
  }

  @Override
  public Warframe updateWarframeSecondary(Long operatorId, Long secondaryWeaponPk) {

    try {
      checkOperator(operatorId);
      checkWeaponAndType(secondaryWeaponPk, WeaponType.SECONDARY);

      return warframeDao.updateWarframeSecondary(operatorId, secondaryWeaponPk);

    } finally {}
  }
  
  @Override
  public Warframe updateWarframeMelee(Long operatorId, Long meleeWeaponPk) {

    try {
      checkOperator(operatorId);
      checkWeaponAndType(meleeWeaponPk, WeaponType.MELEE);

      return warframeDao.updateWarframeMelee(operatorId, meleeWeaponPk);

    } finally {}
  }


  private void checkOperator(Long operatorId) {

    List<Operator> operator = warframeDao.checkOperator(operatorId);
    
    if(operator.isEmpty()) {
      String msg = String.format("No Operator found with ID=%s", operatorId);
      throw new NoSuchElementException(msg);
    }  
  
  }
  
  /*
  private void checkWeapon(Long weaponId) {

    List<Weapon> weapon = warframeDao.checkWeapon(weaponId);
    
    if(weapon.isEmpty()) {
      String msg = String.format("No weapon found with ID=%s", weaponId);
      throw new NoSuchElementException(msg);
    }  

  }
  */

  private void checkWeaponAndType(Long weaponId, WeaponType weaponType) {

    List<Weapon> weapon = warframeDao.checkWeaponAndType(weaponId, weaponType);
    
    if(weapon.isEmpty()) {
      String msg = String.format("No weapon found with ID=%s for type=%s", weaponId, weaponType);
      throw new NoSuchElementException(msg);
    }  

  }
  


}
