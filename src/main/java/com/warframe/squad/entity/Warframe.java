package com.warframe.squad.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Warframe {
  private Long warframe_id;
  private Long operator_id;
  private String warframe_name;
  private Long primary_weapon;
  private Long secondary_weapon;
  private Long melee_weapon;

}
