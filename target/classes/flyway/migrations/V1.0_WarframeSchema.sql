
DROP TABLE IF EXISTS warframe_weapon;
DROP TABLE IF EXISTS warframe;
DROP TABLE IF EXISTS weapons;
DROP TABLE IF EXISTS operator;

CREATE TABLE operator (
  operator_pk int unsigned NOT NULL AUTO_INCREMENT,
  operator_name varchar(40) NOT NULL,
  warframe_fk int unsigned,
  focus_school enum('MADURAI', 'NARAMON', 'UNAIRU', 'VAZARIN', 'ZENURIK') NOT NULL,
  PRIMARY KEY (operator_pk)
);

CREATE TABLE weapons (
  weapon_pk int unsigned NOT NULL AUTO_INCREMENT,
  weapon_name varchar(50) NOT NULL,
  weapon_type enum('PRIMARY', 'SECONDARY', 'MELEE') NOT NULL,
  weapon_desc varchar(50) NOT NULL,
  PRIMARY KEY (weapon_pk)
);

CREATE TABLE warframe (
  warframe_pk int unsigned NOT NULL AUTO_INCREMENT,
  operator_fk int unsigned NOT NULL,
  warframe_name varchar(50) NOT NULL,
  primary_weapon int,
  secondary_weapon int,
  melee_weapon int,
  PRIMARY KEY (warframe_pk),
  FOREIGN KEY (operator_fk) REFERENCES operator (operator_pk) ON DELETE CASCADE
);

CREATE TABLE warframe_weapon (
  warframe_fk int unsigned NOT NULL,
  weapon_fk int unsigned NOT NULL,
  weapon_type enum('PRIMARY', 'SECONDARY', 'MELEE') NOT NULL,
  FOREIGN KEY (warframe_fk) REFERENCES warframe (warframe_pk) ON DELETE CASCADE,
  FOREIGN KEY (weapon_fk) REFERENCES weapons (weapon_pk) ON DELETE CASCADE
);