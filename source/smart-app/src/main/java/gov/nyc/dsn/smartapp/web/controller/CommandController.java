package gov.nyc.dsn.smartapp.web.controller;

import gov.nyc.dsn.smartapp.model.SmartCommand;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommandController {    
    
    @RequestMapping(value = "/processCommand", method = RequestMethod.POST)
    @ResponseBody
    public String processCommand(@RequestBody SmartCommand command){

       System.out.print(command);
       return "Command sent for processing";
    }    

}
