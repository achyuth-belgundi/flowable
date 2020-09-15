package com.achyuth.learning.flowable.engine;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.stereotype.Component;

@Component
public class BizProcessEngine {

	private static ProcessEngine processEngine = null;
	
	public ProcessEngine start() {
		if(processEngine == null) {
			ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
				      .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
				      .setJdbcUsername("sa")
				      .setJdbcPassword("")
				      .setJdbcDriver("org.h2.Driver")
				      .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
	
			processEngine = cfg.buildProcessEngine();
		}
		return processEngine;
	}
	
	public ProcessEngine process() {
		return processEngine;
	}
}
