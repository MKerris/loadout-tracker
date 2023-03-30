package com.warframe.squad.service;

import java.util.List;
import com.warframe.squad.entity.WeaponType;
import com.warframe.squad.entity.Weapon;

public interface WeaponsService {
  
  List<Weapon> fetchWeapons(WeaponType weaponType);

  Weapon saveWeapon(String weaponName, WeaponType weaponType, String weaponDesc);

}
