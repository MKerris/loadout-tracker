package com.warframe.squad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.warframe.ComponentScanMarker;

@SpringBootApplication(scanBasePackageClasses = {ComponentScanMarker.class})    // tells Spring to scan (sub)classes in package from ComponentScanMarker.java
public class WarframeSquad {

  public static void main(String[] args) {
    SpringApplication.run(WarframeSquad.class, args);

  }

}
