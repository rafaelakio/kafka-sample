package br.com.sample.kafka.controller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnviarTopico {

    @Autowired
    private KafkaTemplate<String, String> template;

    private final CountDownLatch latch = new CountDownLatch(3);

    @RequestMapping("/topic1")
    public void enviar1() throws InterruptedException {
    	this.template.send("myTopic", "foo1");
    	this.template.send("myTopic", "foo2");
    	this.template.send("myTopic", "foo3");
    	latch.await(60, TimeUnit.SECONDS);
    }
}
