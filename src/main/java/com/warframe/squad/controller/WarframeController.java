package com.warframe.squad.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.warframe.squad.entity.Warframe;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/warframe")
@OpenAPIDefinition(info = @Info(title = "Warframe Service"), 
  servers = {@Server(url = "http://localhost:8080", description = "Local server")})
public interface WarframeController {

  // @formatter:off
  @Operation(
      summary = "Warframe Loadout",
      
      description = "Returns all Warframes from database",
      
      responses = {
          @ApiResponse(
              responseCode = "200",                                     // 200 = OK
              description = "A list of Warframes is returned.",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Warframe.class))), 
          @ApiResponse(
              responseCode = "400",                                     // 400 = Bad input/request
              description = "Invalid request parameters.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",                                     // 404 = Not found
              description = "No Warframes were found.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",                                     // 500 = Unplanned exception
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      }

  )

  @GetMapping                                                           // Spring will map GET requests at /warframe to the fetchWarframe method
  @ResponseStatus(code = HttpStatus.OK)
    List<Warframe> fetchWarframes();

/*  
  @Operation(
      summary = "Adds a weapon to the table",
      
      description = "Adds a weapon given a weapon name, type, and description.",
      
      responses = {
          @ApiResponse(
              responseCode = "201",                                     // 201 = Created
              description = "Weapon added successfully.",
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "400",                                     // 400 = Bad input/request
              description = "Invalid request parameters.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",                                     // 404 = Not found
              description = "No weapons were found with input criteria.",
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
*/
  // @formatter:on
}
