
-- Operators
INSERT INTO operator (operator_name, warframe_fk, focus_school) VALUES('Hespyr', 1, 'VAZARIN');
INSERT INTO operator (operator_name, warframe_fk, focus_school) VALUES('Nwanda', 2, 'ZENURIK');
INSERT INTO operator (operator_name, warframe_fk, focus_school) VALUES('Amaranthea', 3, 'UNAIRU');
INSERT INTO operator (operator_name, warframe_fk, focus_school) VALUES('OldHobo', 4, 'MADURAI');
INSERT INTO operator (operator_name, warframe_fk, focus_school) VALUES('Redcape', 5, 'NARAMON');

-- Weapons
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Penta', 'PRIMARY', 'Launcher');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Vectis', 'PRIMARY', 'Sniper Rifle');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Supra Vandal', 'PRIMARY', 'Rifle');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Opticor Vanal', 'PRIMARY', 'Rifle');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Phenmor', 'PRIMARY', 'Rifle');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Soma', 'PRIMARY', 'Rifle');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Arca Plasmor', 'PRIMARY', 'Shotgun');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Rubico', 'PRIMARY', 'Sniper Rifle');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Nataruk', 'PRIMARY', 'Bow');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Dread', 'PRIMARY', 'Bow');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Kunai', 'SECONDARY', 'Thrown');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Pyrana', 'SECONDARY', 'Shotgun Sidearm');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Ballistica', 'SECONDARY', 'Crossbow');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Dex Furis', 'SECONDARY', 'Dual Pistols');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Twin Vipers', 'SECONDARY', 'Dual Pistols');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Knell', 'SECONDARY', 'Pistol');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Kraken', 'SECONDARY', 'Pistol');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Lex Prime', 'SECONDARY', 'Pistol');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Pandero', 'SECONDARY', 'Pistol');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Heat Dagger', 'MELEE', 'Dagger');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Dual Skana', 'MELEE', 'Dual Swords');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Ankyros', 'MELEE', 'Fist');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Falcor', 'MELEE', 'Glaive');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Redeemer', 'MELEE', 'Gunblade');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Nikana', 'MELEE', 'Nikana');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Destreza', 'MELEE', 'Rapier');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('War', 'MELEE', 'Sword');
INSERT INTO weapons (weapon_name, weapon_type, weapon_desc) VALUES('Atterax', 'MELEE', 'Whip');

-- Warframes
INSERT INTO warframe (operator_fk, warframe_name, primary_weapon, secondary_weapon, melee_weapon) VALUES(1, 'Mesa Prime', 1, 12, 21);
INSERT INTO warframe (operator_fk, warframe_name, primary_weapon, secondary_weapon, melee_weapon) VALUES(2, 'Volt Prime', 2, 13, 23);
INSERT INTO warframe (operator_fk, warframe_name, primary_weapon, secondary_weapon, melee_weapon) VALUES(3, 'Frost Prime', 3, 14, 25);
INSERT INTO warframe (operator_fk, warframe_name, primary_weapon, secondary_weapon, melee_weapon) VALUES(4, 'Garuda Prime', 4, 15, 27);
INSERT INTO warframe (operator_fk, warframe_name, primary_weapon, secondary_weapon, melee_weapon) VALUES(5, 'Ash Prime', 5, 16, 28);

-- warframe_weapon
INSERT INTO warframe_weapon (warframe_fk, weapon_fk, weapon_type) VALUES (1,1,'PRIMARY');
INSERT INTO warframe_weapon (warframe_fk, weapon_fk, weapon_type) VALUES (1,12,'SECONDARY');
INSERT INTO warframe_weapon (warframe_fk, weapon_fk, weapon_type) VALUES (1,21,'MELEE');
INSERT INTO warframe_weapon (warframe_fk, weapon_fk, weapon_type) VALUES (2,2,'PRIMARY');
INSERT INTO warframe_weapon (warframe_fk, weapon_fk, weapon_type) VALUES (2,13,'SECONDARY');
INSERT INTO warframe_weapon (warframe_fk, weapon_fk, weapon_type) VALUES (2,23,'MELEE');
INSERT INTO warframe_weapon (warframe_fk, weapon_fk, weapon_type) VALUES (3,3,'PRIMARY');
INSERT INTO warframe_weapon (warframe_fk, weapon_fk, weapon_type) VALUES (3,14,'SECONDARY');
INSERT INTO warframe_weapon (warframe_fk, weapon_fk, weapon_type) VALUES (3,25,'MELEE');
INSERT INTO warframe_weapon (warframe_fk, weapon_fk, weapon_type) VALUES (4,4,'PRIMARY');
INSERT INTO warframe_weapon (warframe_fk, weapon_fk, weapon_type) VALUES (4,15,'SECONDARY');
INSERT INTO warframe_weapon (warframe_fk, weapon_fk, weapon_type) VALUES (4,27,'MELEE');
INSERT INTO warframe_weapon (warframe_fk, weapon_fk, weapon_type) VALUES (5,5,'PRIMARY');
INSERT INTO warframe_weapon (warframe_fk, weapon_fk, weapon_type) VALUES (5,16,'SECONDARY');
INSERT INTO warframe_weapon (warframe_fk, weapon_fk, weapon_type) VALUES (5,28,'MELEE');