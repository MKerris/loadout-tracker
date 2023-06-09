package com.warframe.squad.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warframe.squad.dao.OperatorsDao;
import com.warframe.squad.entity.FocusSchool;
import com.warframe.squad.entity.Operator;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultOperatorsService implements OperatorsService {
  
  @Autowired
  private OperatorsDao operatorsDao;

  @Transactional(readOnly = true)
  @Override
  public List<Operator> fetchOperators() {

    log.info("Service: GET Operators");
    
    List<Operator> operators = operatorsDao.fetchOperators();

    return operators;
  }

  
  @Transactional
  @Override
  public Operator newOperator(String operatorName, FocusSchool focusSchool) {
    
    // TODO: Create new warframe with default loadout
    
    return operatorsDao.newOperator(operatorName, focusSchool);
  }

  @Transactional
  @Override
  public void deleteOperator(Long operatorId) {
    
    // TODO: Ensure ON DELETE CASCADE properly kills warframe without killing weapons

    operatorsDao.deleteOperator(operatorId);
  }


  @Override
  public Operator updateOperator(Long operatorId, Long warframeId, FocusSchool focusSchool) {
    
    return operatorsDao.updateOperator(operatorId, warframeId, focusSchool);
  }
  

}
