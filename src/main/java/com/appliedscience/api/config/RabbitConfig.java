package com.appliedscience.api.config;

import com.appliedscience.api.web.controllers.ReceiverController;
import com.appliedscience.api.web.controllers.SenderController;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public TopicExchange topic() {
        return new TopicExchange("ifontys.exchange");
    }


    private static class ReceiverConfig {

        @Bean
        Queue queue() {
            return new Queue("ifontys.queue");
        }

        @Bean
        public Binding bindinga(TopicExchange topic, Queue queue) {
            return BindingBuilder.bind(queue)
                    .to(topic)
                    .with("fhict.sharepoint");
        }

        @Bean
        public Binding bindingb(TopicExchange topic, Queue queue) {
            return BindingBuilder.bind(queue)
                    .to(topic)
                    .with("fhict.canvas");
        }

        @Bean
        public ReceiverController receiver() {
            return new ReceiverController();
        }
    }

    @Bean
    public SenderController sender() {
        return new SenderController();
    }
}
