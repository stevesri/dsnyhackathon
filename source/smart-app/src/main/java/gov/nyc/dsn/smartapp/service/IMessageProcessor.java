package gov.nyc.dsn.smartapp.service;

import gov.nyc.dsn.smartapp.model.SmartCommand;

public interface IMessageProcessor {

	public abstract void processMessage(SmartCommand message);

}