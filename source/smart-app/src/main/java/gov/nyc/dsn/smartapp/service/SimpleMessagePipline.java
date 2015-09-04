package gov.nyc.dsn.smartapp.service;

import gov.nyc.dsn.smartapp.model.SmartCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.springframework.stereotype.Service;

/*
 * Simple implementation of Message Pipline/Processor
 * Composite Message Processor
 */
@Service
public class SimpleMessagePipline implements IMessageProcessor, IMessageQueuer {
	
	public SimpleMessagePipline(){
		messageProcessors.add(new ResquencingMessageProcessor());
		messageProcessors.add(new SmartMessageProcessor());
		messageProcessors.add(new PostMessageProcessor());
	}
	
	List<IMessageProcessor> messageProcessors = new ArrayList<IMessageProcessor>();	
	
	Queue<SmartCommand> queue = new PriorityQueue<SmartCommand>();

	@Override
	public void processMessage(SmartCommand message) {
		messageProcessors.stream().forEach( x -> x.processMessage(message));
	}

	@Override
	public void queueMessage(SmartCommand message) {
		queue.add(message);
	}
	
	@Override
	public SmartCommand dequeueMessage(){
		return queue.poll();
	}
	
}
