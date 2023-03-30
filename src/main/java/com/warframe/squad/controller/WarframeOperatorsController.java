package com.warframe.squad.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.warframe.squad.entity.Operator;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/operators")
@OpenAPIDefinition(info = @Info(title = "Warframe Operators Service"), 
  servers = {@Server(url = "http://localhost:8080", description = "Local server")})
public interface WarframeOperatorsController {

  // @formatter:off
  @Operation(
      summary = "Returns a list of Operators",
      
      description = "Returns a list of all Operators in database",
      
      responses = {
          @ApiResponse(
              responseCode = "200",                                     // 200 = OK
              description = "A list of Operators is returned.",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Operator.class))), 
          @ApiResponse(
              responseCode = "400",                                     // 400 = Bad input/request
              description = "Invalid request parameters.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",                                     // 404 = Not found
              description = "No Operators were found with input criteria.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",                                     // 500 = Unplanned exception
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      }
  )

  @GetMapping                                                           // Spring will map GET requests at /weapons to the fetchWeapons method
  @ResponseStatus(code = HttpStatus.OK)
    List<Operator> fetchOperators();

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

  // @formatter:on
*/  
}
