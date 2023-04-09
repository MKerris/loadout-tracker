package com.warframe.squad.dao;

import java.util.List;
import com.warframe.squad.entity.Operator;
import com.warframe.squad.entity.Warframe;
import com.warframe.squad.entity.Weapon;
import com.warframe.squad.entity.WeaponType;

public interface WarframeDao {
  
  List<Warframe> fetchWarframes();

  Warframe fetchOperatorWarframe(Long operatorId);

  Warframe updateWarframeName(Long operatorId, String warframeName);

  Warframe updateWarframePrimary(Long operatorId, Long primaryWeaponPk);

  Warframe updateWarframeSecondary(Long operatorId, Long secondaryWeaponPk);

  Warframe updateWarframeMelee(Long operatorId, Long meleeWeaponPk);
  
  List<Operator> checkOperator(Long operatorId);

  List<Weapon> checkWeapon(Long weaponId);

  List<Weapon> checkWeaponAndType(Long weaponId, WeaponType weaponType);


}
