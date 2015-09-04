package gov.nyc.dsn.smartapp.web.controller;

import gov.nyc.dsn.smartapp.model.SmartCommand;
import gov.nyc.dsn.smartapp.service.IMessageQueuer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommandController {    
    
	@Autowired
	IMessageQueuer messageQueuer;
	
    @RequestMapping(value = "/processCommand", method = RequestMethod.POST)
    @ResponseBody
    public String processCommand(@RequestBody SmartCommand command){

       System.out.println(command);
       messageQueuer.queueMessage(command);
       return "Command sent for processing";
    }    

}
