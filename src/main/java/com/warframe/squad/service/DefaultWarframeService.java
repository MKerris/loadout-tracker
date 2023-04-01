package com.warframe.squad.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warframe.squad.dao.WarframeDao;
import com.warframe.squad.entity.Warframe;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultWarframeService implements WarframeService {

  @Autowired
  private WarframeDao warframeDao;
  
  @Transactional(readOnly = true)
  @Override
  public List<Warframe> fetchWarframes() {
    
    log.info("Service: GET Warframes");
    
    List<Warframe> warframes = warframeDao.fetchWarframes();

    return warframes;
  }

  @Override
  public Warframe updateWarframe(Long operatorId, String warframeName, Long primaryWeapon,
      Long secondaryWeapon, Long meleeWeapon) {

    return warframeDao.updateWarframe(operatorId, warframeName, primaryWeapon, secondaryWeapon, meleeWeapon);
  }

  
}
