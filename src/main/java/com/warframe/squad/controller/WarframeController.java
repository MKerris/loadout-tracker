package com.warframe.squad.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.warframe.squad.entity.Operator;
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
      summary = "Updates a Warframe",
      
      description = "Updates a Warframe given for a specific Operator ID and uppdates Warframe name, primary, secondary, and/or melee weapon(s)",
      
      responses = {
          @ApiResponse(
              responseCode = "200",                                     // 200 = OK
              description = "Warframe was updated.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",                                     // 404 = Not found
              description = "Warframe was not found.",
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
              description = "Name of Warframe"),
          @Parameter(
              name = "primaryWeapon", 
              allowEmptyValue = false, 
              required = true, 
              description = "Primary weapon value"),
          @Parameter(
              name = "secondaryWeapon", 
              allowEmptyValue = false, 
              required = true, 
              description = "Secondary weapon value"),
          @Parameter(
              name = "meleeWeapon", 
              allowEmptyValue = false, 
              required = true, 
              description = "Melee weapon value")
      }

  )
  
  @PutMapping("/updateWarframe")
  @ResponseStatus(code = HttpStatus.OK)
    Warframe updateWarframe(
        @RequestParam(required = true) Long operatorId,
        @RequestParam(required = true) String warframeName,
        @RequestParam(required = true) Long primaryWeapon,
        @RequestParam(required = true) Long secondaryWeapon,
        @RequestParam(required = true) Long meleeWeapon
        );   

  
/*
  @Operation(
      summary = "Adds a Warframe",
      
      description = "Adds a Warframe (only used when a new Operator is added) ",
      
      responses = {
          @ApiResponse(
              responseCode = "201",                                     // 201 = Created
              description = "Warframe added successfully.",
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404",                                     // 404 = Not found
              description = "Not found.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "409",                                     // 409 = Conflict
              description = "Warframe with input criteria already exists.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",                                     // 500 = Unplanned exception
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },

      parameters = {
          @Parameter(
              name = "warframeName",
              allowEmptyValue = false,
              required = true,
              description = "Name of Warframe"),
          @Parameter(
              name = "primaryWeapon", 
              allowEmptyValue = false, 
              required = true, 
              description = "Primary weapon value"),
          @Parameter(
              name = "secondaryWeapon", 
              allowEmptyValue = false, 
              required = true, 
              description = "Secondary weapon value"),
          @Parameter(
              name = "meleeWeapon", 
              allowEmptyValue = false, 
              required = true, 
              description = "Melee weapon value")
      }

  )
  
  @PostMapping("/addWarframe")
  @ResponseStatus(code = HttpStatus.CREATED)
    Warframe newWarframe(
        @RequestParam(required = true) Long warframeName,
        @RequestParam(required = true) Long primaryWeapon,
        @RequestParam(required = true) Long secondaryWeapon,
        @RequestParam(required = true) Long meleeWeapon
        );

  
  @Operation(
      summary = "Deletes a Warframe",
      
      description = "Deletes a Warframe given a Warframe ID (only used when Operator is deleted)",
      
      responses = {
          @ApiResponse(
              responseCode = "200",                                     // 200 = OK
              description = "Warframe was deleted.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",                                     // 404 = Not found
              description = "Warframe was not found.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",                                     // 500 = Unplanned exception
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },

      parameters = {
          @Parameter(
              name = "warframeId",
              allowEmptyValue = false,
              required = true,
              description = "PK of Warframe")
      }

  )
  
  @DeleteMapping("/removeWarframe")
  @ResponseStatus(code = HttpStatus.OK)
    void deleteWarframe(
        @RequestParam(required = true) Long warframeId
        ); 

*/
  // @formatter:on
}
