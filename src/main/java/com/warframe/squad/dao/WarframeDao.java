package com.warframe.squad.dao;

import java.util.List;
import com.warframe.squad.entity.Warframe;

public interface WarframeDao {
  
  List<Warframe> fetchWarframes();

  Warframe updateWarframe(Long operatorId, String warframeName, Long primaryWeapon,
      Long secondaryWeapon, Long meleeWeapon);

  Warframe fetchOperatorWarframe(Long operatorId);
  

}
