package com.example.demo.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class LoanApprovalResult implements JavaDelegate {
    private boolean response; // Boolean field

    // Getter for 'response'
    public boolean getResponse() {
        return response;
    }

    // Setter for 'response'
    public void setResponse(boolean response) {
        this.response = response;
    }
    @Override
    public void execute(DelegateExecution execution) {
        boolean loanResultResponse = (boolean) execution.getVariable("loanResultResponse");
        System.out.println("loanResultResponse is:"+ loanResultResponse);
        setResponse(loanResultResponse);
        System.out.println("Loan result response is:"+ loanResultResponse);
    }
}
