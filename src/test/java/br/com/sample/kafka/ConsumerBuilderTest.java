package br.com.sample.kafka;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.sample.kafka.config.ConsumerBuilder;

/**
 * Testes unitários para {@link ConsumerBuilder}.
 *
 * Validam a API fluente (setters retornam {@code this}) sem envolver
 * conexões reais com broker Kafka — o método {@code create()} é
 * intencionalmente não invocado para evitar a instanciação de um
 * {@link org.apache.kafka.clients.consumer.KafkaConsumer} real.
 */
class ConsumerBuilderTest {

    private ConsumerBuilder newBuilder() {
        return new ConsumerBuilder() {
            @Override
            public void callback(String res) {
                // no-op para o teste
            }
        };
    }

    @Test
    void builderInstanceShouldBeNonNull() {
        ConsumerBuilder builder = newBuilder();
        assertNotNull(builder);
    }

    @Test
    void setGroupIdShouldBeFluent() {
        ConsumerBuilder builder = newBuilder();
        ConsumerBuilder result = builder.setGroupId("my-group");
        assertSame(builder, result, "setGroupId deve retornar a mesma instância");
    }

    @Test
    void setGroupIdShouldIgnoreNullValues() {
        ConsumerBuilder builder = newBuilder();
        ConsumerBuilder result = builder.setGroupId(null);
        assertSame(builder, result);
    }

    @Test
    void setGroupIdShouldIgnoreEmptyValues() {
        ConsumerBuilder builder = newBuilder();
        ConsumerBuilder result = builder.setGroupId("");
        assertSame(builder, result);
    }

    @Test
    void setEndpointZookeeperListShouldBeFluent() {
        ConsumerBuilder builder = newBuilder();
        List<String> endpoints = Arrays.asList("broker1:9092", "broker2:9092");
        ConsumerBuilder result = builder.setEndpointZookeeperList(endpoints);
        assertSame(builder, result);
    }

    @Test
    void setEndpointZookeeperListShouldIgnoreNull() {
        ConsumerBuilder builder = newBuilder();
        ConsumerBuilder result = builder.setEndpointZookeeperList(null);
        assertSame(builder, result);
    }
}
