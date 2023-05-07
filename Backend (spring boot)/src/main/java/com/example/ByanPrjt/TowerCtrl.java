package com.example.ByanPrjt;

import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
public class TowerCtrl {
    @RequestMapping("/tower")
    @ResponseBody
    private Tower[] getTower() {
        String uri = "https://byanat.wiremockapi.cloud/api/v3/towers";
        RestTemplate restTemplate = new RestTemplate();
        Tower[] tower;
        //get data from api
//        ArrayT tower = restTemplate.getForObject(uri, ArrayT.class);
        tower = restTemplate.getForObject(uri, Tower[].class);

//        for (int i = 0; i < tower.length - 1; i++) {
//            System.out.println("Tower Id: " + tower[i].getTower_id());
//            System.out.println("Operator: " + tower[i].getOperator());
//            System.out.println("Technology: " + tower[i].getTechnology());
//            System.out.println("Tower Type: " + tower[i].getTechnology());
//            System.out.println("Height: " + tower[i].getTechnology());
//            System.out.println("Latitude: " + tower[i].getLatitude());
//            System.out.println("Longitude: " + tower[i].getLongitude());
//            System.out.println("Address: " + tower[i].getAddress());
//            System.out.println("\n");
//        }

        //display result in browser: http://localhost:8080/challenge/tower
        return tower;
    }

    // Searching function
    @ResponseBody
    @GetMapping("/challenge/towers")
    public Tower[] filtering(@RequestParam(required = false) String operator, @RequestParam(required = false) String tech, @RequestParam(required = false) String type) {
        String uri = "https://byanat.wiremockapi.cloud/api/v3/towers";
        RestTemplate restTemplate = new RestTemplate();
        //convert stream to array of type Tower to get tower objects
        Tower[] tower;
        tower = restTemplate.getForObject(uri, Tower[].class);
        Tower[] ntower;

        //check if not all null then search
        if (tech != null || operator != null || type != null) {
            //assign new variable to take the resulting data from search
            Stream<Tower> nto;
            //make sure we have the towers data
            assert tower != null;
            nto = Arrays.stream(tower).filter((e) -> e != null
                    && (tech == null || Objects.equals(e.getTechnology().toLowerCase(), tech.toLowerCase()))
                    && (operator == null || Objects.equals(e.getOperator().toLowerCase(), operator.toLowerCase()))
                    && (type == null || Objects.equals(e.getTower_type().toLowerCase(), type.toLowerCase())));
            //convert the resulting data to class Tower again
            ntower = nto.toArray(Tower[]::new);

            ntower = Arrays.stream(ntower)
                    .filter(Objects::nonNull)
                    .toArray(Tower[]::new);
            //if no search results
            if (ntower.length < 1) {
                System.out.println("No Search results.. try http://localhost:8080/challenge/towers?operator=Verizon&tech=4G&type=TOWER");
                return tower;
            }
        } else {
            //if not searching then just display all
            System.out.println("Not getting any search parameters.. to search try http://localhost:8080/challenge/towers?operator=Verizon&tech=4G&type=TOWER");
            return tower;
        }

        return ntower;
    }

}
