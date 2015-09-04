package gov.nyc.dsn.smartapp.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

/*
 *  Poor man's implementation of a sequence registry.
 *  In a multi-jvm setup this would not work. 
 *  The value needs to come from db
 */
@Service
public class SequenceRegistry implements ISequenceRegistry {
	
	Map<String,Long> sequenceMap = new ConcurrentHashMap<String, Long>();
	
	/* (non-Javadoc)
	 * @see hello.ISequenceRegistry#getNextSequence(java.lang.String)
	 */
	@Override
	public synchronized long getLastSequence(String sequenceScope){
		if( !sequenceMap.containsKey(sequenceScope)){
			sequenceMap.put(sequenceScope, 0L);
		}
		return sequenceMap.get(sequenceScope);
	}	
	
	/* (non-Javadoc)
	 * @see hello.ISequenceRegistry#setNextSequence(java.lang.String, long)
	 */
	@Override
	public  synchronized void setNextSequence(String sequenceScope, long sequenceId){
		sequenceMap.put(sequenceScope, sequenceId);
	}
	
	/* (non-Javadoc)
	 * @see hello.ISequenceRegistry#getNextSequence(java.lang.String)
	 */
	@Override
	public synchronized long getNextSequence(String sequenceScope){
		long lastVal = sequenceMap.get(sequenceScope);
		sequenceMap.put(sequenceScope, ++lastVal);
		return sequenceMap.get(sequenceScope);
	}	

}
