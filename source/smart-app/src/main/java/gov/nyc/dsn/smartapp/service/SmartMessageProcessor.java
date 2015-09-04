package gov.nyc.dsn.smartapp.service;

import gov.nyc.dsn.smartapp.model.SmartCommand;

public class SmartMessageProcessor implements IMessageProcessor {

	@Override
	public void processMessage(SmartCommand message) {
		System.out.println("SmartMessageProcessor processed message");

	}

}
