package com.appliedscience.api.config;

import com.appliedscience.api.web.controllers.ReceiverController;
import com.appliedscience.api.web.controllers.SenderController;
import org.springframework.amqp.core.*;
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
        Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding bindinga(TopicExchange topic, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(topic)
                    .with("fhict.sharepoint");
        }

        @Bean
        public Binding bindingb(TopicExchange topic, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
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
