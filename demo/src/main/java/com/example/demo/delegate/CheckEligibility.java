package com.example.demo.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class CheckEligibility implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        // Retrieve variables from the process context
        double loanAmount = (double) execution.getVariable("loanamount");
        double accountBalance = (double) execution.getVariable("accountbalance");

        // Debugging logs (optional)
        System.out.println("Loan Amount: " + loanAmount);
        System.out.println("Account Balance: " + accountBalance);

        // Check if the loan amount is less than 5 times the account balance
         boolean isEligible = loanAmount < (5 * accountBalance);

        // Set the result variable
        execution.setVariable("loanResult", isEligible);

        // Debugging log to confirm the eligibility result
        System.out.println("loanResult: " + isEligible);
    }
}
