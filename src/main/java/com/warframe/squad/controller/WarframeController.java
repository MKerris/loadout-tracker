package com.warframe.squad.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.warframe.squad.entity.Warframe;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
      summary = "Returns details of a Warframe",
      
      description = "Returns a list of Warframes",
      
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

  @GetMapping("/getWarframes")
  @ResponseStatus(code = HttpStatus.OK)
    List<Warframe> fetchWarframes();

  
  @Operation(
      summary = "Update Warframe name",
      
      description = "Update the Warframe name for a given Operator",
      
      responses = {
          @ApiResponse(
              responseCode = "200",                                     // 200 = OK
              description = "Warframe name was updated.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",                                     // 404 = Not found
              description = "Operator was not found.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",                                     // 500 = Unplanned exception
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },

      parameters = {
          @Parameter(
              name = "operatorId",
              allowEmptyValue = false,
              required = true,
              description = "ID of Operator"),
          @Parameter(
              name = "warframeName",
              allowEmptyValue = false,
              required = true,
              description = "Name of Warframe")      
      }

  )
  
  @PutMapping("/updateWarframeName")
  @ResponseStatus(code = HttpStatus.OK)
    Warframe updateWarframeName(
        @RequestParam(required = true) Long operatorId,
        @RequestParam(required = true) String warframeName
        ); 

 
  
  @Operation(
      summary = "Update Warframe Primary Weapon",
      
      description = "Update the primary weapon for a given Operator",
      
      responses = {
          @ApiResponse(
              responseCode = "200",                                     // 200 = OK
              description = "Primary weapon was updated.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",                                     // 404 = Not found
              description = "Weapon was not found.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",                                     // 500 = Unplanned exception
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },

      parameters = {
          @Parameter(
              name = "operatorId",
              allowEmptyValue = false,
              required = true,
              description = "ID of Operator"),
          @Parameter(
              name = "primaryWeaponPk",
              allowEmptyValue = false,
              required = true,
              description = "PK of Weapon (PRIMARY weapon_type)")      
      }

  )
  
  @PutMapping("/updateWarframePrimary")
  @ResponseStatus(code = HttpStatus.OK)
    Warframe updateWarframePrimary(
        @RequestParam(required = true) Long operatorId,
        @RequestParam(required = true) Long primaryWeaponPk
        ); 
  
  
  @Operation(
      summary = "Update Warframe Secondary Weapon",
      
      description = "Update the secondary weapon for a given Operator",
      
      responses = {
          @ApiResponse(
              responseCode = "200",                                     // 200 = OK
              description = "Secondary weapon was updated.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",                                     // 404 = Not found
              description = "Weapon was not found.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",                                     // 500 = Unplanned exception
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },

      parameters = {
          @Parameter(
              name = "operatorId",
              allowEmptyValue = false,
              required = true,
              description = "ID of Operator"),
          @Parameter(
              name = "secondaryWeaponPk",
              allowEmptyValue = false,
              required = true,
              description = "PK of Weapon (SECONDARY weapon_type)")      
      }

  )
  
  @PutMapping("/updateWarframeSecondary")
  @ResponseStatus(code = HttpStatus.OK)
    Warframe updateWarframeSecondary(
        @RequestParam(required = true) Long operatorId,
        @RequestParam(required = true) Long secondaryWeaponPk
        );

  
  @Operation(
      summary = "Update Warframe Melee Weapon",
      
      description = "Update the melee weapon for a given Operator",
      
      responses = {
          @ApiResponse(
              responseCode = "200",                                     // 200 = OK
              description = "Melee weapon was updated.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",                                     // 404 = Not found
              description = "Weapon was not found.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",                                     // 500 = Unplanned exception
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },

      parameters = {
          @Parameter(
              name = "operatorId",
              allowEmptyValue = false,
              required = true,
              description = "ID of Operator"),
          @Parameter(
              name = "meleeWeaponPk",
              allowEmptyValue = false,
              required = true,
              description = "PK of Weapon (SECONDARY weapon_type)")      
      }

  )
  
  @PutMapping("/updateWarframeMelee")
  @ResponseStatus(code = HttpStatus.OK)
    Warframe updateWarframeMelee(
        @RequestParam(required = true) Long operatorId,
        @RequestParam(required = true) Long meleeWeaponPk
        );
  
  // @formatter:on
}
