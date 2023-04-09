package com.warframe.squad.service;

import java.util.List;
import com.warframe.squad.entity.Warframe;

public interface WarframeService {
  
  List<Warframe> fetchWarframes();

  Warframe updateWarframeName(Long operatorId, String warframeName);

  Warframe updateWarframePrimary(Long operatorId, Long primaryWeaponPk);

  Warframe updateWarframeSecondary(Long operatorId, Long secondaryWeaponPk);

  Warframe updateWarframeMelee(Long operatorId, Long meleeWeaponPk);


}
