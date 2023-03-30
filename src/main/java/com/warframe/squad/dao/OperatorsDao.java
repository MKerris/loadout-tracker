package com.warframe.squad.dao;

import java.util.List;
import com.warframe.squad.entity.Operator;

public interface OperatorsDao {
  
  List<Operator> fetchOperators();
  
//  Weapon saveWeapon(String weaponName, WeaponType weaponType, String weaponDesc);

}
