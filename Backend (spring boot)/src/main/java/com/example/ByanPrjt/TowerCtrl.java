package com.example.ByanPrjt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



@Controller
public class TowerCtrl {
//    @RequestMapping("/challenge/towers?networkoperator={op}&technology={tech}")
    @RequestMapping("/tower")
    @ResponseBody
//    private ModelAndView getTower(@PathVariable String tech , Model model) {
        private String getTower() {
        String uri = "https://byanat.wiremockapi.cloud/api/v3/towers/";
        RestTemplate restTemplate = new RestTemplate();
        ArrayT tower = restTemplate.getForObject(uri, ArrayT.class);

        assert tower != null;
        System.out.println("Tower_id: " + tower.ar().getTower_id());
        System.out.println("Operator: " + tower.ar().getOperator());
        System.out.println("Tech: " + tower.ar().getTechnology());


//        ModelAndView modelAndView = new ModelAndView("tower");
//        modelAndView.addObject("tower_id", tower.ar().getTower_id());
//        modelAndView.addObject("operator", tower.ar().getOperator());
//        modelAndView.addObject("address", tower.ar().getAddress());
//        modelAndView.addObject("height", tower.ar().getHeight());
//        modelAndView.addObject("tower_type", tower.ar().getTower_type());
//        modelAndView.addObject("latitude", tower.ar().getLatitude());
//        modelAndView.addObject("longitude", tower.ar().getLongitude());
//        modelAndView.addObject("Technology", tower.ar().getTechnology());

        return "modelAndView";
    }

    @RequestMapping("/hello")
    @ResponseBody
    private String hello() {
        return "Hello World";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/towers")
    public String controllerMethod(@RequestParam Map<String, String> customQuery) {

        System.out.println("customQuery = brand " + customQuery.get("operator"));
        System.out.println("customQuery = limit " + customQuery.get("technology"));
        System.out.println("customQuery = price " + customQuery.get("tower_type"));


        return customQuery.toString();
    }
}
