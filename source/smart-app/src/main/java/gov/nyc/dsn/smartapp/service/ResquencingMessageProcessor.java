package gov.nyc.dsn.smartapp.service;

import gov.nyc.dsn.smartapp.exception.SequenceOutOfOrderException;
import gov.nyc.dsn.smartapp.model.SmartCommand;

public class ResquencingMessageProcessor implements IMessageProcessor {

	ISequenceRegistry sequenceRegistry;
	
	//dont like this, but for now
	public ResquencingMessageProcessor(ISequenceRegistry sequenceRegistry){
		this.sequenceRegistry = sequenceRegistry;
	}
	
	@Override
	public void processMessage(SmartCommand message) {
		System.out.println("ResquencingMessageProcessor About to process message " + message.getSequenceId());
		long lastSeq = sequenceRegistry.getLastSequence(message.getSequenceScope());
		if(message.getSequenceId() - lastSeq != 1){
			System.out.println("ResquencingMessageProcessor throwing out of sequence exception");
			throw new SequenceOutOfOrderException(" Message " + message.getName() + " out of sequence, Last sequence " + 
					lastSeq + " current sequence " + message.getSequenceId());
		}
		System.out.println("ResquencingMessageProcessor processed message " + message.getSequenceId());

	}

}
