package com.warframe.squad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.warframe.squad.entity.Warframe;
import com.warframe.squad.service.WarframeService;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class DefaultWarframeController implements WarframeController {

  @Autowired
  private WarframeService warframeService;
  
  @Override
  public List<Warframe> fetchWarframes() {

    log.debug("Controller: GET Warframes");
    
    return warframeService.fetchWarframes();
    
  }


  @Override
  public Warframe updateWarframeName(Long operatorId, String warframeName) {

    log.debug("Controller: updateWarframeName() operatorId={}, warframeName={}", operatorId, warframeName);
    
    return warframeService.updateWarframeName(operatorId, warframeName);
    
  }

  @Override
  public Warframe updateWarframePrimary(Long operatorId, Long primaryWeaponPk) {

    log.debug("Controller: updateWarframeName() operatorId={}, warframeName={}", operatorId, primaryWeaponPk);
    
    return warframeService.updateWarframePrimary(operatorId, primaryWeaponPk);
  }

  @Override
  public Warframe updateWarframeSecondary(Long operatorId, Long secondaryWeaponPk) {

    log.debug("Controller: updateWarframeName() operatorId={}, warframeName={}", operatorId, secondaryWeaponPk);
    
    return warframeService.updateWarframeSecondary(operatorId, secondaryWeaponPk);
  }

  @Override
  public Warframe updateWarframeMelee(Long operatorId, Long meleeWeaponPk) {

    log.debug("Controller: updateWarframeName() operatorId={}, warframeName={}", operatorId, meleeWeaponPk);
    
    return warframeService.updateWarframeMelee(operatorId, meleeWeaponPk);
  }


}
