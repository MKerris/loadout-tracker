package com.warframe.squad.dao;

import java.util.List;
import com.warframe.squad.entity.Warframe;

public interface WarframeDao {
  
  List<Warframe> fetchWarframes();
  
//Weapon saveWeapon(String weaponName, WeaponType weaponType, String weaponDesc);

}
