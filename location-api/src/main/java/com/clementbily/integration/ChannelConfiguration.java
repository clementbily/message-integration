package com.clementbily.integration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ChannelConfiguration {
    private static final String CLIENT_TOPIC = "client";

    @Bean
    MessageChannel client() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public KafkaMessageDrivenChannelAdapter<String, String>
    adapter(KafkaMessageListenerContainer<String, String> container) {
        KafkaMessageDrivenChannelAdapter<String, String> kafkaMessageDrivenChannelAdapter =
                new KafkaMessageDrivenChannelAdapter<>(container, KafkaMessageDrivenChannelAdapter.ListenerMode.record);
        kafkaMessageDrivenChannelAdapter.setOutputChannel(client());
        return kafkaMessageDrivenChannelAdapter;
    }

    @Bean
    public KafkaMessageListenerContainer<String, String> container(ConsumerFactory<String, String> consumerFactory) throws Exception {
        ContainerProperties properties = new ContainerProperties(CLIENT_TOPIC);
        return new KafkaMessageListenerContainer<>(consumerFactory, properties);
    }


    @Bean
    public NewTopic topic() {
        return new NewTopic(CLIENT_TOPIC, 1, (short) 1);
    }
}
