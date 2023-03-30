package com.warframe.squad.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Weapon {

  private Long weapon_pk;
  private String weapon_name;
  private WeaponType weapon_type;
  private String weapon_desc;

}
