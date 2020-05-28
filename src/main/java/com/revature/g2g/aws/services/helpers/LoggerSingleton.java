package com.revature.g2g.aws.services.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoggerSingleton {
	private Logger accessDeniedLog;
	private Logger businessLog;
	private Logger discordLog;
	private Logger exceptionLog;
	private Logger performanceLog;
	public Logger getAccessLog() {
		if(accessDeniedLog == null) {
			accessDeniedLog = LogManager.getLogger("accessLogger");
		}
		return accessDeniedLog;
	}
	public Logger getBusinessLog() {
		if(businessLog == null) {
			businessLog = LogManager.getLogger("businessLogger");
		}
		return businessLog;
	}
	public Logger getDiscordLog() {
		if(discordLog == null) {
			discordLog = LogManager.getLogger("discordLogger");
		}
		return discordLog;
	}
	public Logger getExceptionLogger() {
		if (exceptionLog == null) {
			exceptionLog =  LogManager.getLogger("exceptionLogger");
		}
		return exceptionLog;
	}
	public Logger getPerformanceLog() {
		if(performanceLog == null) {
			performanceLog = LogManager.getLogger("performanceLogger");
		}
		return performanceLog;
	}
}
