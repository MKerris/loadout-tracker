package com.warframe.squad.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class WeaponRequest {
  
  @NotNull
  @Length(max = 50)
  @Pattern(regexp = "[\\w\\s]*")
  private String weapon_name;
  
  @NotNull
  private WeaponType weaponType;
  
  @NotNull
  @Length(max = 50)
  @Pattern(regexp = "[\\w\\s]*")
  private String weapon_desc;

}
