package com.warframe.squad.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.warframe.squad.entity.WeaponType;
import com.warframe.squad.entity.Weapon;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/weapons")                                             // Any request coming to /weapons will be mapped to this class
@OpenAPIDefinition(info = @Info(title = "Warframe Weapons Service"), 
  servers = {@Server(url = "http://localhost:8080", description = "Local server")})
public interface WeaponsController {

  // @formatter:off
  @Operation(
      summary = "Returns a list of weapons",
      
      description = "Returns a list of weapons given a weapon type (Primary, Secondary, or Melee)",
      
      responses = {
          @ApiResponse(
              responseCode = "200",                                     // 200 = OK
              description = "A list of weapons is returned.",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Weapon.class))), 
          @ApiResponse(
              responseCode = "400",                                     // 400 = Bad input/request
              description = "Invalid request parameters.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",                                     // 404 = Not found
              description = "No Weapons were found.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",                                     // 500 = Unplanned exception
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },

      parameters = {
          @Parameter(
              name = "weaponType", 
              allowEmptyValue = false, 
              required = false, 
              description = "Weapon type of Primary, Secondary, or Melee")
      }

  )

  @GetMapping                                                           // Spring will map GET requests at /weapons to the fetchWeapons method
  @ResponseStatus(code = HttpStatus.OK)
    List<Weapon> fetchWeapons(@RequestParam(required = true) WeaponType weaponType);

  
  @Operation(
      summary = "Adds a weapon to the table",
      
      description = "Adds a weapon given a weapon name, type, and description.",
      
      responses = {
          @ApiResponse(
              responseCode = "201",                                     // 201 = Created
              description = "Weapon added successfully.",
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404",                                     // 404 = Not found
              description = "Not found.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "409",                                     // 409 = Conflict
              description = "Weapon with input criteria already exists.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",                                     // 500 = Unplanned exception
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },

      parameters = {
          @Parameter(
              name = "weaponName",
              allowEmptyValue = false,
              required = true,
              description = "Name of weapon"),
          @Parameter(
              name = "weaponType", 
              allowEmptyValue = false, 
              required = true, 
              description = "Weapon type of Primary, Secondary, or Melee"),
          @Parameter(
              name = "weaponDesc",
              allowEmptyValue = false,
              required = true,
              description = "Description of type of weapon (Bow, Pistol, Rifle, etc.)")
      }

  )
  
  @PostMapping("/addWeapon")
  @ResponseStatus(code = HttpStatus.CREATED)
    Weapon saveWeapon(
        @RequestParam(required = true) String weaponName,
        @RequestParam(required = true) WeaponType weaponType,
        @RequestParam(required = true) String weaponDesc
        );

  // @formatter:on
}