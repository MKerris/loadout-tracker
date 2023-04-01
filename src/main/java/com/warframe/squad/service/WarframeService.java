package com.warframe.squad.service;

import java.util.List;
import com.warframe.squad.entity.Warframe;

public interface WarframeService {
  
  List<Warframe> fetchWarframes();

  Warframe updateWarframe(Long operatorId, String warframeName, Long primaryWeapon,
      Long secondaryWeapon, Long meleeWeapon);


}
