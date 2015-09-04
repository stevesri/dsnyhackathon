package gov.nyc.dsn.smartapp.service;

import gov.nyc.dsn.smartapp.exception.SequenceOutOfOrderException;
import gov.nyc.dsn.smartapp.model.SmartCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

/*
 * Simple implementation of Message Pipline/Processor
 * Composite Message Processor
 */
@Service
public class SimpleMessagePipline implements IMessageProcessor, IMessageQueuer, InitializingBean  {
	
	@Autowired
	ISequenceRegistry sequenceRegistry;
	
	public SimpleMessagePipline(){
	}
	
	List<IMessageProcessor> messageProcessors = new ArrayList<IMessageProcessor>();	
	
	//should probably be a prirority queue
	Queue<SmartCommand> queue = new ConcurrentLinkedQueue<SmartCommand>();

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
	
   public void afterPropertiesSet() {
	      System.out.println("AfterInitialization : ");
			messageProcessors.add(new ResquencingMessageProcessor(sequenceRegistry));
			messageProcessors.add(new SmartMessageProcessor());
			messageProcessors.add(new PostMessageProcessor(sequenceRegistry));	
			ExecutorService executor = Executors.newFixedThreadPool(2)	;
			executor.execute(new Runnable() {
			    public void run() {
			    	while(true){
			    		System.out.println("Asynchronous task");
			    		SmartCommand message = queue.poll();
			    		if(message != null){
			    			try{
			    				processMessage(message);
			    			}catch(SequenceOutOfOrderException e){
			    				queue.add(message);
			    			}
			    		}
			    		try{
			    			Thread.sleep(1000);
			    		} catch(InterruptedException e){}
			    	}
			    }
			});				
	   }
   

}


