package com.warframe.squad.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warframe.squad.dao.OperatorsDao;
import com.warframe.squad.entity.Operator;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultOperatorsService implements OperatorsService {
  
  @Autowired
  private OperatorsDao operatorsDao;

  @Transactional(readOnly = true)
  @Override
  public List<Operator> fetchOperators() {

    log.info("Service: GET Operators");
    
    List<Operator> operators = operatorsDao.fetchOperators();

    return operators;
  }

  
/*
  @Transactional
  @Override
  public Weapon saveWeapon(String weaponName, WeaponType weaponType, String weaponDesc) {
    
    // Break object into three variables to write values to DB
    
    return warframeWeaponsDao.saveWeapon(weaponName, weaponType, weaponDesc);
  }
*/

}
