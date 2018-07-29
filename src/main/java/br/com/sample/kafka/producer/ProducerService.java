package br.com.sample.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

	@Autowired
    private KafkaTemplate<String, String> template;
	
//	private final CountDownLatch latch = new CountDownLatch(3);
	
	public void send(
		final String msg
	) {
		try {
			template.send("myTopic", msg);
//			latch.await(60, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void send2(
			final String msg
		) {
			try {
				template.send("myTopic1", msg);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
}
