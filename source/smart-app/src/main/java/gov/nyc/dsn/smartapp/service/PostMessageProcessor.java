package gov.nyc.dsn.smartapp.service;

import gov.nyc.dsn.smartapp.model.SmartCommand;

public class PostMessageProcessor implements IMessageProcessor{

	@Override
	public void processMessage(SmartCommand message) {
		System.out.println("PostMessageProcessor processed message");
		
	}

	
	
}
