package gov.nyc.dsn.smartapp.service;

import gov.nyc.dsn.smartapp.model.SmartCommand;

public class PostMessageProcessor implements IMessageProcessor{


	ISequenceRegistry sequenceRegistry;
	
	//dont like this, but for now
	public PostMessageProcessor(ISequenceRegistry sequenceRegistry){
		this.sequenceRegistry = sequenceRegistry;
	}	
	
	@Override
	public void processMessage(SmartCommand message) {
		System.out.println("PostMessageProcessor to process message " + message.getSequenceId());
		sequenceRegistry.setNextSequence(message.getSequenceScope(), message.getSequenceId());
		System.out.println("PostMessageProcessor processed message " + message.getSequenceId());
		
	}

	
	
}
