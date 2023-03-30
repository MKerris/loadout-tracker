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

}
