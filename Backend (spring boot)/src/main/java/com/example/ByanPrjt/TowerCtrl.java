package com.example.ByanPrjt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Controller
public class TowerCtrl {
    @RequestMapping("/tower")
    @ResponseBody
    private String getTower() {
        String uri = "https://byanat.wiremockapi.cloud/api/v3/towers";
        RestTemplate restTemplate = new RestTemplate();
        ArrayT tower = restTemplate.getForObject(uri, ArrayT.class);

        if( tower != null) {
            System.out.println("Tower_id: " + tower.ar().getTower_id());
            System.out.println("Operator: " + tower.ar().getOperator());
            System.out.println("Tech: " + tower.ar().getTechnology());
        } else {return "cannot read data";}

        return "It is working";
    }

    @RequestMapping("/hello")
    @ResponseBody
    private String hello() {
        return "Hello World";
    }

}
