package com.warframe.squad.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loadout {

  private Long warframeFk;
  private Long primaryWeaponPk;
  private String primaryWeaponName;
  private Long secondaryWeaponPk;
  private String secondaryWeaponName;
  private Long meleeWeaponPk;
  private String meleeWeaponName;
  
}
