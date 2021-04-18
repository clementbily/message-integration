package com.clementbily.integration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.kafka.outbound.KafkaProducerMessageHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class ChannelConfiguration {
    private static final String CLIENT_TOPIC = "client";

    @Bean
    MessageChannel client() {
        return new PublishSubscribeChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "client")
    public MessageHandler handler(KafkaTemplate<String, String> kafkaTemplate) throws Exception {
        KafkaProducerMessageHandler<String, String> handler =
                new KafkaProducerMessageHandler<>(
                        kafkaTemplate);
        handler.setTopicExpression(new LiteralExpression(CLIENT_TOPIC));
        handler.setMessageKeyExpression(new LiteralExpression("addedClient"));
        return handler;
    }


    @Bean
    public NewTopic topic() {
        return new NewTopic(CLIENT_TOPIC, 1, (short) 1);
    }
}
