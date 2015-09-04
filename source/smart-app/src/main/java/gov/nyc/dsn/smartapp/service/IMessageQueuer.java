package gov.nyc.dsn.smartapp.service;

import gov.nyc.dsn.smartapp.model.SmartCommand;

public interface IMessageQueuer {

	public abstract void queueMessage(SmartCommand message);
	
	public abstract SmartCommand dequeueMessage();

}