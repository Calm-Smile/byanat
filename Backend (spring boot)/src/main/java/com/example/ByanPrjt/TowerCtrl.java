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

        //display result in browser: http://localhost:8080/challenge/tower
        String nto=Arrays.toString(tower);
        return "<br><div style='position: absolute;text-align:center;\n" +
                "  top: 50%;\n" +
                "  left: 50%;\n" +
                "  width: 100%;\n" +
                "  max-height: calc(100vh - 1px);\n" +
                "    overflow: auto;\n" +
                "    border-radius: 2px;\n" +
                "    max-width: 100%;\n" +
                "  -webkit-transform: translate(-50%, -50%);\n" +
                "          transform: translate(-50%, -50%);'><h2>List of all towers&nbsp;(" + tower.length +" towers):</h2><table  style=\"text-align: center; margin: 0 auto; border-left:1px solid black; border-right:1px solid black;\" border=1 frame=hsides rules=rows>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td><strong>&nbsp;ID&nbsp;</strong></td>\n" +
                "<td style=\"text-align: center; border-left:1px solid black;\"><strong>&nbsp;Operator&nbsp;</strong></td>\n" +
                "<td style=\"text-align: center; border-left:1px solid black;\"><strong>&nbsp;Address&nbsp;</strong></td>\n" +
                "<td style=\"text-align: center; border-left:1px solid black;\"><strong>&nbsp;Height (m)&nbsp;</strong></td>\n" +
                "<td style=\"text-align: center; border-left:1px solid black;\"><strong>&nbsp;Type&nbsp;</strong></td>\n" +
                "<td style=\"text-align: center; border-left:1px solid black;\"><strong>&nbsp;Latitude&nbsp;</strong></td>\n" +
                "<td style=\"text-align: center; border-left:1px solid black;\"><strong>&nbsp;Longitude&nbsp;</strong></td>\n" +
                "<td style=\"text-align: center; border-left:1px solid black;\"><strong>&nbsp;Technology&nbsp;</strong></td>\n" +
                "</tr>" +
                "<tr>"+nto.replace("[", "")
                .replace(",", "\n")
                .replace("]", "</tbody></table><br><h3>&nbsp;End of table</h3></div>");
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
                return "<br><div style='position: absolute;text-align:center;\n" +
                        "  top: 50%;\n" +
                        "  left: 50%;\n" +
                        "  width: 100%;\n" +
                        "  max-height: calc(100vh - 1px);\n" +
                        "    overflow: auto;\n" +
                        "    border-radius: 7px;\n" +
                        "    max-width: 100%;\n" +
                        "  -webkit-transform: translate(-50%, -50%);\n" +
                        "          transform: translate(-50%, -50%);'><h2>We've found&nbsp;" + ntower.length +" towers for you:</h2><table  style=\"text-align: center; margin: 0 auto; border-left:1px solid black; border-right:1px solid black;\" border=1 frame=hsides rules=rows>\n" +
                        "<tbody>\n" +
                        "<tr>\n" +
                        "<td><strong>&nbsp;ID&nbsp;</strong></td>\n" +
                        "<td style=\"text-align: center; border-left:1px solid black;\"><strong>&nbsp;Operator&nbsp;</strong></td>\n" +
                        "<td style=\"text-align: center; border-left:1px solid black;\"><strong>&nbsp;Address&nbsp;</strong></td>\n" +
                        "<td style=\"text-align: center; border-left:1px solid black;\"><strong>&nbsp;Height (m)&nbsp;</strong></td>\n" +
                        "<td style=\"text-align: center; border-left:1px solid black;\"><strong>&nbsp;Type&nbsp;</strong></td>\n" +
                        "<td style=\"text-align: center; border-left:1px solid black;\"><strong>&nbsp;Latitude&nbsp;</strong></td>\n" +
                        "<td style=\"text-align: center; border-left:1px solid black;\"><strong>&nbsp;Longitude&nbsp;</strong></td>\n" +
                        "<td style=\"text-align: center; border-left:1px solid black;\"><strong>&nbsp;Technology&nbsp;</strong></td>\n" +
                        "</tr>" +
                        "<tr>"+Arrays.toString(ntower).replace("[", "")
                        .replace(",", "")
                        .replace("]", "</tbody></table><br><h3>&nbsp;End of search results</h3></div>");
        } else {
            //if not searching then just display all
            return ("""
                    <html>
                    <body>
                    <div style="position: absolute;
                                  top: 50%;
                                  left: 50%;
                                  width: 90%;
                                  max-height: calc(100vh - 155px);
                                    overflow: auto;
                                    border-radius: 7px;
                                    max-width: 100%;
                                  -webkit-transform: translate(-50%, -50%);
                                          transform: translate(-50%, -50%);">
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
                        </div></body>\s
                          </html>""");
        }
    }
}
