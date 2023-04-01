package com.warframe.squad.dao;

import java.util.List;
import com.warframe.squad.entity.FocusSchool;
import com.warframe.squad.entity.Operator;

public interface OperatorsDao {
  
  List<Operator> fetchOperators();
  
  Operator newOperator(String operatorName, FocusSchool focusSchool);

  void deleteOperator(Long operatorId);

  Operator updateOperator(Long operatorId, Long warframeId, FocusSchool focusSchool);

  Operator fetchOperator(Long operatorId);

}
