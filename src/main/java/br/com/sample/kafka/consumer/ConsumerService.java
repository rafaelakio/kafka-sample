package br.com.sample.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerService {

//    private final CountDownLatch latch1 = new CountDownLatch(1);

    @KafkaListener(id = "foo", topics = "myTopic")
    public void listen1(String foo) {
    	System.out.println(String.format("myTopicListener1 = %s", foo));
//        this.latch1.countDown();
    	RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.getForObject("http://localhost:8080/kafka/send2?msg=teste123", String.class);
        System.out.println(
        		res
        );
    }
    
    @KafkaListener(topics = "myTopic1")
    public void listen2(String args) {
    	System.out.println(String.format("myTopicListener2 = %s", args));
//        this.latch1.countDown();
    }

}
