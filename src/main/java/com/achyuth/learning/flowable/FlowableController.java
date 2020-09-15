package com.achyuth.learning.flowable;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.achyuth.learning.flowable.vo.ValueObject;

@RestController
@RequestMapping("flowable")
public class FlowableController {

	public static final Logger logger = LoggerFactory.getLogger(FlowableController.class);
	
	@Autowired
	FlowableService service;
	
	@GetMapping("start")
	public void startEngine() {
		service.start();
	}
	
	@GetMapping("deploy")
	public void deploy() {
		service.deploy();
	}
	
	@GetMapping("trigger") 
	public void triggerDummyTasks(){
		
		ValueObject vo = new ValueObject();
		vo.setId("achyub");
		vo.setName("firstName");
		vo.setCity("silk city");
		
		service.trigger(vo);
	}
	
	@PostMapping("trigger") 
	public void trigger(@RequestBody ValueObject vo){
		service.trigger(vo);
	}
	
	@GetMapping("approve")
	public void approve(@RequestParam String assignee, @RequestParam int taskNr ) {
		service.approve(assignee, taskNr);
	}
	
	@GetMapping("reject")
	public void reject(@RequestParam String assignee, @RequestParam int taskNr ) {
		service.approve(assignee, taskNr);
	}	
	
	@GetMapping("task/{assignee}")
	public List<ValueObject> task(@PathParam(value = "assignee") String assignee) {
		return service.getTasksByAssignee(assignee);
	}
	
	@GetMapping("task")
	public List<ValueObject> getTask(@RequestParam String assignee) {
		return service.getTasksByAssignee(assignee);
	}
	
	@GetMapping("whereIsMyTask")
	public String getStatus(@RequestParam String assignee, @RequestParam int taskNr) {
		return service.status(assignee, taskNr);
	}
}
