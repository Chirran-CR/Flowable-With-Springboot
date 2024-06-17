package com.example.demo.delegate;

import io.swagger.annotations.Scope;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class AdditionOfTwoNumberUsingServiceTaskNew implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        // Example logic for the service task
        String input1 = String.valueOf(execution.getVariable("number2"));
        String input2 = String.valueOf(execution.getVariable("number1"));

        int result = Integer.parseInt(input1) + Integer.parseInt(input2);
        execution.setVariable("result", result);

        System.out.println("Executed MyServiceTaskDelegate with result: " + result);
    }
}