package com.example.demo;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
//import org.flowable.engine.test.Deployment;
import org.flowable.engine.repository.Deployment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner init(final RepositoryService repositoryService,
//								  final RuntimeService runtimeService,
//								  final TaskService taskService) {
//		return new CommandLineRunner() {
//			@Override
//			public void run(String... strings) throws Exception {
//				// Deploy the process definition manually
//				Deployment deployment = (Deployment) repositoryService.createDeployment()
//						.addClasspathResource("processes/Addition_of_Two_Number_using_service_task.bpmn20.xml")
//						.deploy();
//
//				System.out.println("Deployment class name: " + deployment.getClass());
//				System.out.println("Deployment Details:"+ deployment);
//
////				System.out.println("Number of process definitions : "
////						+ repositoryService.createProcessDefinitionQuery().count());
////				System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
////				runtimeService.startProcessInstanceByKey("additonOfTwoNumber");
////				System.out.println("Number of tasks after process start: "
////						+ taskService.createTaskQuery().count());
//			}
//		};
//	}
}
