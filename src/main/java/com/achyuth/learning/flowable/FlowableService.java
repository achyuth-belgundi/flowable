package com.achyuth.learning.flowable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achyuth.learning.flowable.engine.BizProcessEngine;
import com.achyuth.learning.flowable.vo.ValueObject;

@Service
public class FlowableService {
	
	public static final Logger logger = LoggerFactory.getLogger(FlowableService.class);

	@Autowired
	BizProcessEngine engine;
	
	public void start() {
		engine.start();
	}	
	
	public String trigger(ValueObject vo) {		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employee", vo);
		ProcessInstance processInstance = engine.process().getRuntimeService().startProcessInstanceByKey("holidayRequest", variables);
		String processInstanceId = processInstance.getId();
		Task task = engine.process().getTaskService().createTaskQuery().processInstanceId(processInstanceId).singleResult();
		task.setAssignee(vo.getId()); 
		engine.process().getTaskService().saveTask(task);
		
		return processInstance.getId();
	}
	
	public void deploy() {
		RepositoryService repositoryService = engine.process().getRepositoryService();
		Deployment deployment = repositoryService.createDeployment()
		  .addClasspathResource("./process/holiday-request.bpmn20.xml")
		  .deploy();
		logger.info("Deployment Time:" + deployment.getDeploymentTime());
	}
	
	public List<ValueObject> getTasksByAssignee(String assignee) {
		logger.info("assignee - "+assignee);
		System.out.println("assignee - "+assignee);
		List<Task> tasks = engine.process().getTaskService().createTaskQuery().taskAssignee(assignee).list();
		
		return tasks.stream().map(
					task -> {
						Map<String, Object> variables = engine.process().getTaskService().getVariables(task.getId());
						return (ValueObject) variables.get("employee");
					}).collect(Collectors.toList());
	}
	
	public void approve(String assignee, int taskNr) {
		//write a code to approve
		
		HashMap variables = new HashMap<String, Object>();
		variables.put("approved", true);
		Task task = engine.process().getTaskService().createTaskQuery().taskAssignee(assignee).list().get(taskNr);//.processVariableValueEquals("employee", vo);
		engine.process().getTaskService().complete(task.getId(), variables);
	}
	
	public void reject(String assignee, int taskNr) {
		//write a code to approve
		
		HashMap variables = new HashMap<String, Object>();
		variables.put("approved", false);
		Task task = engine.process().getTaskService().createTaskQuery().taskAssignee(assignee).list().get(taskNr);//.processVariableValueEquals("employee", vo);
		engine.process().getTaskService().complete(task.getId(), variables);
	}
	
	public String status(String assignee, int taskNr) {
		return engine.process().getTaskService().createTaskQuery().taskAssignee(assignee).list().get(taskNr).toString();
//		return engine.process().getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().toString();
	}
}
