package com.example.demo.controller;

import com.example.demo.service.MyService;
import org.flowable.engine.RepositoryService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class MyRestController {
    @Autowired
    private MyService myService;
    @Autowired
    private RepositoryService repositoryService;

    @GetMapping("/deploy")
    public String deployProcess() {
        repositoryService.createDeployment()
                .addClasspathResource("processes/Loan_Approval_Process.bpmn20.xml")
                .addClasspathResource("processes/Loan_eligibility_check.bpmn20.xml")
                .deploy();
        return "Process deployed successfully!";
    }
    @PostMapping(value="/process")
    public void startProcessInstance() {
        myService.startProcess();
    }

//    @RequestMapping(value="/tasks", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
//    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
//        List<org.flowable.task.api.Task> tasks = myService.getTasks(assignee);
//        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
//        for (Task task : tasks)
//            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
//        return dtos;
//    }
    @GetMapping("/tasksids")
    public List<Map<String, Object>> getAllTaskOfaProccess(@RequestParam String processInstanceId) {
        List<Task> tasks =  myService.getAllTaskOfaProcess(processInstanceId);
        // Convert tasks to a list of maps to avoid lazy loading issues
        return tasks.stream().map(task -> {
            Map<String, Object> taskMap = new HashMap<>();
            taskMap.put("id", task.getId());
            taskMap.put("name", task.getName());
            taskMap.put("assignee", task.getAssignee());
            taskMap.put("createTime", task.getCreateTime());
            // Add other necessary fields
            return taskMap;
        }).collect(Collectors.toList());
    }

    @PostMapping("/complete")
    public String completeUserTask(@RequestParam String taskId, @RequestBody Map<String, Object> requestBody) {
        try {
            // Extract values from the request body
            String name = (String) requestBody.get("name");
            Double accountBalance = ((Number) requestBody.get("accountBalance")).doubleValue();
            Double loanAmount = ((Number) requestBody.get("loanAmount")).doubleValue();

            // Add the extracted values to the variables map
            Map<String, Object> variables = new HashMap<>();
            variables.put("name", name);
            variables.put("accountbalance", accountBalance);
            variables.put("loanamount", loanAmount);

            System.out.println("Values are: Names=" + name +
                    ", AccountBalance=" + accountBalance +
                    ", LoanAmount=" + loanAmount);

            // Complete the task using the service
            myService.completeTask(taskId, variables);
            return "User task completed successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to complete user task!";
        }
    }

    static class TaskRepresentation {
        private String id;
        private String name;
        public TaskRepresentation(String id, String name) {
            this.id = id;
            this.name = name;
        }
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }}
