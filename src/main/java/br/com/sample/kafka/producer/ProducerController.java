package br.com.sample.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class ProducerController {

    @Autowired
    private ProducerService sender;

    @GetMapping("/send")
    public String send(
    		final @RequestParam(value="msg", required=false) String message
    ) {
    	String msg = message == null || message.isEmpty() ? "foo1" : message;
    	this.sender.send(msg);
    	
    	return "Mensagem enviada";
    }
    
    @GetMapping("/send2")
    public String send2(
    		final @RequestParam(value="msg", required=false) String message
    ) {
    	String msg = message == null || message.isEmpty() ? "foo1" : message;
    	this.sender.send2(msg);
    	
    	return "Mensagem enviada";
    }
}
