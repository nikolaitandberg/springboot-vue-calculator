package no.ntnu.idatt2105.V25.oving5.web;

import no.ntnu.idatt2105.V25.oving5.model.Calculation;
import no.ntnu.idatt2105.V25.oving5.service.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import no.ntnu.idatt2105.V25.oving5.model.CalculationRequest;
import no.ntnu.idatt2105.V25.oving5.model.CalculationResponse;
import no.ntnu.idatt2105.V25.oving5.service.CalcaulatorService;

import java.util.List;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    @Autowired
    private CalcaulatorService service;

    @Autowired
    private JWTService jwtService;

    Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    @PostMapping("/calculate")
    //    @ResponseBody() er unødvendig når vi bruker @RestController, mapping til JSON skjer automagisk
    public CalculationResponse calculate(@RequestBody CalculationRequest calculationRequest, @RequestHeader("Authorization") String token) {

        String username = jwtService.extractUserName(token.substring(7));
        logger.info("User {} is making a calculation request", username);

//        logger.trace("ermahgerd trers");
//        logger.debug("ermahgerd derberg");
//        logger.info("ermahgerd ernferh");
//        logger.warn("ermahgerd wern");
//        logger.error("ermahgerd ehrrerr");


        return this.service.calculate(calculationRequest, username);
    }

    @PostMapping("/calculations")
    public List<Calculation> calculations(@RequestHeader("Authorization") String token) {
        String username = jwtService.extractUserName(token.substring(7));
        logger.info("User {} is making a request to retrieve calculations", username);

        return this.service.calculations(username);
    }

}
