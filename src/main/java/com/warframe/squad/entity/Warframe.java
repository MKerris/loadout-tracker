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
  private Integer warframe_id;
  private Integer operator_id;
  private String warframe_name;
  private Integer primary_weapon;
  private Integer secondary_weapon;
  private Integer melee_weapon;

}
