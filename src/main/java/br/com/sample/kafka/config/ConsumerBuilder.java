package br.com.sample.kafka.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public abstract class ConsumerBuilder {
	
	private static final String addressZookeeperServer = "127.0.0.1:9092";
	private static final String groupIdTopic = "group_id";

	private List<String> addressZookeeper = new ArrayList<>();
	private Consumer<Long, String> consumer;
	private String groupId = "";
	private String topico = "";
	
	public ConsumerBuilder() {
		addressZookeeper.add(addressZookeeperServer);
		groupId = groupIdTopic;
	}
	
	public ConsumerBuilder setEndpointZookeeperList(List<String> list) {
		if (list != null) {
			addressZookeeper = list;
		}
		return this;
	}
	
	public ConsumerBuilder setGroupId(String group) {
		if (group != null && !group.isEmpty()) {
			groupId = group;
		}
		return this;
	}
	
	public ConsumerBuilder create(String topico) {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, addressZookeeper);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//		factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(props));
		consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList(topico));
		this.topico = topico;
		return this;
	}
	
	public void run() {
		final int timePolling = 1000; // recuperar da variavel de ambiente
		new Thread() {
			public void run() {
				while(true) {
					final ConsumerRecords<Long, String> response = consumer.poll(timePolling);
					response.forEach(record -> {
						System.out.println(
								String.format(
									"record:(%d, %s, %d, %d)\n",
									record.key(), record.value(),
									record.partition(), record.offset()
								)
							);
						callback(record.value());
					});
					consumer.commitAsync();
				}
			}
		}.start();
		System.out.println(
				String.format("criou consumer para => %s", topico)
		);
		//consumer.close();
	}
	
	public abstract void callback(String res);
}
