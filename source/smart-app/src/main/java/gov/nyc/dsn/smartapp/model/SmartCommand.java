package gov.nyc.dsn.smartapp.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SmartCommand implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String sequenceScope;
	
	private long sequenceId;
	
	private String message;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSequenceScope() {
		return sequenceScope;
	}

	public void setSequenceScope(String sequenceScope) {
		this.sequenceScope = sequenceScope;
	}

	public long getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(long sequenceId) {
		this.sequenceId = sequenceId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
	

}
