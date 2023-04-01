package com.warframe.squad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.warframe.squad.entity.FocusSchool;
import com.warframe.squad.entity.Operator;
import com.warframe.squad.service.OperatorsService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultOperatorsController implements OperatorsController {

  @Autowired
  private OperatorsService operatorsService;
  
  @Override
  public List<Operator> fetchOperators() {

    log.debug("Controller: GET Operators");
    
    return operatorsService.fetchOperators();
  }

  @Override
  public Operator newOperator(String operatorName, FocusSchool focusSchool) {

    log.debug("operatorName={}, focusSchool={}", operatorName, focusSchool);
    
    return operatorsService.newOperator(operatorName, focusSchool);

  }

  @Override
  public void deleteOperator(Long operatorId) {
    
    log.debug("operatorId={}", operatorId);

    operatorsService.deleteOperator(operatorId);
  }

  @Override
  public Operator updateOperator(Long operatorId, Long warframeId, FocusSchool focusSchool) {
    
    log.debug("operatorId={}, warframeId={}, focusSchool={}", operatorId, warframeId, focusSchool);

    return operatorsService.updateOperator(operatorId, warframeId, focusSchool);
  }

}
