package com.example.demo.service;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyService {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Transactional
    public void startProcess() {
        System.out.println("Inside start process");
        Map<String, Object> variables = new HashMap<>();
        variables.put("initiator", "admin"); // Replace with the actual user ID or reference
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("processDefinitionKey", variables);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("loanProcess",variables);
        // Verify process instance creation
        if (processInstance != null && processInstance.getId() != null) {
            System.out.println("Process instance created successfully with ID: " + processInstance.getId());
            System.out.println("Process definition ID: " + processInstance.getProcessDefinitionId());
        } else {
            System.out.println("Failed to create process instance. and processInstance is:"+ processInstance);
        }
    }
    @Transactional
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }
    @Transactional
    public List<Task> getAllTaskOfaProcess(String processInstanceId){
        return taskService.createTaskQuery().processInstanceId(processInstanceId).list();
    }
    @Transactional
    public void completeTask(String taskId, Map<String, Object>  variables){
        System.out.println("Val of variable inside service is:"+variables);
        taskService.complete(taskId, variables);
    }
}
