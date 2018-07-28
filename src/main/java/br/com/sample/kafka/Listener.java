package br.com.sample.kafka;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Listener {

    private final CountDownLatch latch1 = new CountDownLatch(1);

    @KafkaListener(id = "foo", topics = "myTopic")
    public void listen1(String foo) {
    	System.out.println(String.format("myTopicListener = %s", foo));
        this.latch1.countDown();
    }
    
    @KafkaListener(topics = "myTopic1")
    public void listen2(ConsumerRecords<?, ?> args) {
    	System.out.println(String.format("myTopicListener = %s", args));
        this.latch1.countDown();
    }

}
