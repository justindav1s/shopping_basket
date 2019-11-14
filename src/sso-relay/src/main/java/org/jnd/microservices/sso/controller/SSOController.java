package org.jnd.microservices.sso.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class SSOController {

    private static final Logger log = LoggerFactory.getLogger(SSOController.class);


    @RequestMapping(value = "/handle-oauth", method = RequestMethod.GET, produces = "application/json")
    public String login(@RequestHeader HttpHeaders headers, @RequestParam(name = "code") String code) {

        log.info("handle-oauth");
        log.info("code : "+code);

        for (String key : headers.keySet()) {
            log.info(key+" : "+headers.get(key));
        }

        return "handle-oauth";
    }



    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String ping() {
        return "OK";
    }


}
