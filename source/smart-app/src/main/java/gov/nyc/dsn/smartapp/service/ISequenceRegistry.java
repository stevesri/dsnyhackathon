package gov.nyc.dsn.smartapp.service;

public interface ISequenceRegistry {

	public abstract long getLastSequence(String sequenceScope);
	
	public abstract long getNextSequence(String sequenceScope);

	public abstract void setNextSequence(String sequenceScope, long sequenceId);
	

}