package br.com.sample.kafka;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;

import br.com.sample.kafka.producer.ProducerBuilder;

/**
 * Testes unitários para {@link ProducerBuilder}.
 *
 * Esses testes verificam apenas a criação do {@link KafkaTemplate}
 * e seus parâmetros de configuração, sem enviar mensagens reais a
 * um broker Kafka.
 */
class ProducerBuilderTest {

    @Test
    void shouldCreateKafkaTemplateInstance() {
        KafkaTemplate<String, String> template = ProducerBuilder.create();

        assertNotNull(template, "ProducerBuilder.create() deve retornar uma instância não nula");
    }

    @Test
    void templateShouldStartWithoutDefaultTopic() {
        KafkaTemplate<String, String> template = ProducerBuilder.create();

        assertNull(template.getDefaultTopic(), "KafkaTemplate não deve possuir default topic");
    }

    @Test
    void successiveCallsShouldReturnDistinctTemplates() {
        KafkaTemplate<String, String> t1 = ProducerBuilder.create();
        KafkaTemplate<String, String> t2 = ProducerBuilder.create();

        assertNotNull(t1);
        assertNotNull(t2);
        // Cada chamada cria uma nova factory/template.
        assert t1 != t2;
    }
}
