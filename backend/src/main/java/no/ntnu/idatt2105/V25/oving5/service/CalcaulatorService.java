package no.ntnu.idatt2105.V25.oving5.service;

import no.ntnu.idatt2105.V25.oving5.model.Calculation;
import no.ntnu.idatt2105.V25.oving5.model.User;
import no.ntnu.idatt2105.V25.oving5.repo.CalculationRepository;
import no.ntnu.idatt2105.V25.oving5.repo.UserRepository;
import tk.pratanumandal.expr4j.ExpressionEvaluator;


import org.springframework.stereotype.Service;

import no.ntnu.idatt2105.V25.oving5.model.CalculationRequest;
import no.ntnu.idatt2105.V25.oving5.model.CalculationResponse;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CalcaulatorService {

    private final CalculationRepository calculationRepository;
    private final UserRepository userRepository;
    ExpressionEvaluator exprEval = new ExpressionEvaluator();

    public CalcaulatorService(CalculationRepository calculationRepository, UserRepository userRepository) {
        this.calculationRepository = calculationRepository;
        this.userRepository = userRepository;
    }


    public CalculationResponse calculate(CalculationRequest calculationRequest, String username) {

        CalculationResponse response = new CalculationResponse(null);
        String expString = calculationRequest.getExpression();

        try {

            Double result = exprEval.evaluate(expString);
            System.out.println(result);
            response.setResult(result.toString());

            if (Double.isInfinite(result)) {
                System.out.println("Number is infinity, returning error");
                response.setResult("Error");
            }

            User user = userRepository.findByUsername(username);

            Calculation calculation = new Calculation(expString, response.getResult(), user);
            calculationRepository.save(calculation);

        } catch (Exception e) {
            System.out.println("Error evaluating expression: " + e.getMessage());
            response.setResult("Error");
        }

        return response;
    }


    public List<Calculation> calculations(String username) {
        System.out.println("username:" + username);
        User user = userRepository.findByUsername(username);
        System.out.println("User:" + user);
        List<Calculation> calculations = calculationRepository.findAllByUser(user);
        System.out.println(calculations);
        return calculations;
    }
}
