package com.example.ByanPrjt;

import org.springframework.cglib.core.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
public class TowerCtrl {
    @RequestMapping("/challenge/tower")
    @ResponseBody
    private String getTower() {
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
        String nto=Arrays.toString(tower).replace("[", "<br><h2>List of all towers:</h2>")
                .replace("]", "<br><h3>&nbsp;End of list</h3>");;
        return nto;
    }

    // Searching function
    @ResponseBody
    @GetMapping("/challenge/towers")
    public String filtering(@RequestParam(required = false) String operator, @RequestParam(required = false) String tech, @RequestParam(required = false) String type) {
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
                return ("""
                        <p>&nbsp;</p>
                        <h2>&nbsp;No Search result found..</h2>
                        <h3>&nbsp;Try</h3>
                        <a href='http://localhost:8080/challenge/towers?operator=Verizon&amp;tech=4G&amp;type=TOWER'>http://localhost:8080/challenge/towers?operator=Verizon&amp;tech=4G&amp;type=TOWER</a>""");
//                return Arrays.toString(tower);
            } else
                return Arrays.toString(ntower).replace("[", "<br><h2>We've found "+ ntower.length +" towers for you:</h2>")
                        .replace("]", "<br><h3>&nbsp;End of search results</h3>");
        } else {
            //if not searching then just display all
            return ("""
                    <html>
                    <body>
                    <h3>Not getting any search parameters or you've used wrong parameters!</h3>
                    <table  style="text-align: center; border-left:1px solid black; border-right:1px solid black;" border=1 frame=hsides rules=rows>
                    <tbody>
                    <tr>
                    <td><strong>&nbsp;Parameter&nbsp;</strong></td>
                    <td style="text-align: center; border-left:1px solid black;"><strong>Usage</strong></td>
                    <td style="text-align: center; border-left:1px solid black;"><strong>Example</strong></td>
                    </tr>
                    <tr>
                    <td>operator</td>
                    <td style="text-align: center; border-left:1px solid black;">For finding network operators of towers</td>
                    <td style="text-align: center; border-left:1px solid black;">Verzion, AT&amp;T</td>
                    </tr>
                    <tr>
                    <td>tech</td>
                    <td style="text-align: center; border-left:1px solid black;">&nbsp;For finding the technology used in towers&nbsp;</td>
                    <td style="text-align: center; border-left:1px solid black;">3G, 4G, 5G</td>
                    </tr>
                    <tr>
                    <td>type</td>
                    <td style="text-align: center; border-left:1px solid black;">For finding the type of towers</td>
                    <td style="text-align: center; border-left:1px solid black;">&nbsp;TOWER, GTOWER&nbsp;</td>
                    </tr>
                    </tbody>
                    </table>
                    <div>&nbsp;</div>
                    <div>For example, try:</div>
                    <a href='http://localhost:8080/challenge/towers?operator=Verizon&amp;tech=4G&amp;type=TOWER'>http://localhost:8080/challenge/towers?operator=Verizon&amp;tech=4G&amp;type=TOWER</a>
                        </body>\s
                          </html>""");
        }
    }
}
