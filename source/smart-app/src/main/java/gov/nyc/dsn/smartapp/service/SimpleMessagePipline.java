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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

/*
 * Simple implementation of Message Pipline/Processor
 * Composite Message Processor
 */
@Service
public class SimpleMessagePipline implements IMessageProcessor, IMessageQueuer,BeanPostProcessor  {
	
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
	
	//dont like this but for now
   public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
	      System.out.println("BeforeInitialization : " + beanName);
			messageProcessors.add(new ResquencingMessageProcessor(sequenceRegistry));
			messageProcessors.add(new SmartMessageProcessor());
			messageProcessors.add(new PostMessageProcessor(sequenceRegistry));	
			ExecutorService executor = Executors.newSingleThreadExecutor();
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
			    			Thread.sleep(5000);
			    		} catch(InterruptedException e){}
			    	}
			    }
			});				
	      return bean;  // you can return any other object as well
	   }
   
   public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
	      System.out.println("BeforeInitialization : " + beanName);
	      return bean;  // you can return any other object as well
	   }

	
}


