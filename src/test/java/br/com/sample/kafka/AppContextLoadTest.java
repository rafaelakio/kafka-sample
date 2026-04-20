package br.com.sample.kafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.sample.kafka.consumer.ConsumerService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Teste simples de carregamento do contexto Spring.
 *
 * <p>Exclui explicitamente o {@code KafkaAutoConfiguration} e mocka o
 * {@link ConsumerService} para que o contexto suba sem exigir um
 * broker Kafka real. O endereço do broker pode ser sobrescrito via
 * variável de ambiente {@code TEST_BROKER}.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = AppContextLoadTest.TestApp.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = {
                "spring.kafka.bootstrap-servers=${TEST_BROKER:localhost:9092}",
                "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration"
        }
)
class AppContextLoadTest {

    @MockBean
    private ConsumerService consumerService;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertNotNull(applicationContext, "O contexto Spring deve estar disponível");
        assertNotNull(consumerService, "O bean ConsumerService (mock) deve estar registrado");
    }

    /**
     * Configuração de teste mínima: habilita auto-configuration do Spring Boot
     * mas não faz component scan do pacote de produção, evitando que beans
     * como {@code ConsumerKafkaConfig} (que habilita {@code @EnableKafka})
     * e listeners reais sejam instanciados durante o teste de contexto.
     */
    @org.springframework.boot.autoconfigure.SpringBootApplication
    static class TestApp {
    }
}
