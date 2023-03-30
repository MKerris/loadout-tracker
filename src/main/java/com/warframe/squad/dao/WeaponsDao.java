package com.warframe.squad.dao;

import java.util.List;
import com.warframe.squad.entity.WeaponType;
import com.warframe.squad.entity.Weapon;

public interface WeaponsDao {
  
  List<Weapon> fetchWeapons(WeaponType weaponType);
  
  Weapon saveWeapon(String weaponName, WeaponType weaponType, String weaponDesc);

}
