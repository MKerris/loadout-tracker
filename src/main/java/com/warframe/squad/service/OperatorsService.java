package com.warframe.squad.service;

import java.util.List;
import com.warframe.squad.entity.Operator;

public interface OperatorsService {
  
  List<Operator> fetchOperators();

}
