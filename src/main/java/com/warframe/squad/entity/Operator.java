package com.warframe.squad.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Operator {
  private Long operator_id;
  private String operator_name;
  private Long warframe_id;
  private FocusSchool focus_school;

}
