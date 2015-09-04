package gov.nyc.dsn.smartapp.web.controller;

import gov.nyc.dsn.smartapp.model.SmartCommand;
import gov.nyc.dsn.smartapp.service.IMessageProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommandController {    
    
	@Autowired
	IMessageProcessor messageProcessor;
	
    @RequestMapping(value = "/processCommand", method = RequestMethod.POST)
    @ResponseBody
    public String processCommand(@RequestBody SmartCommand command){

       System.out.println(command);
       messageProcessor.processMessage(command);
       return "Command sent for processing";
    }    

}
