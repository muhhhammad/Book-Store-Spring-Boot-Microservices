package com.practice.order.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.order.ApplicationProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.QueueBuilder;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class RabbitMQConfig {

    private final ApplicationProperties properties;

    @Autowired
    public RabbitMQConfig(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(properties.orderEventExchange());
    }

    @Bean
    public Queue newOrdersQueue(){
        return QueueBuilder.durable(properties.newOrdersQueue()).build();
    }

//    @Bean
//    public MappingJackson2HttpMessageConverter jacksonMessageConverter(ObjectMapper objectMapper) {
//        // Custom configurations
//        return new MappingJackson2HttpMessageConverter(objectMapper);
//    }


    @Bean
    public Binding newOrdersQueueBinding(){
        return BindingBuilder.bind(newOrdersQueue())
                .to(directExchange())
                .with(properties.newOrdersQueue());
    }

    @Bean
    public Queue deliveredOrdersQueue(){
        return QueueBuilder.durable(properties.deliveredOrdersQueue()).build();
    }

    @Bean
    public Binding deliveredOrdersQueueBinding(){
        return BindingBuilder.bind(deliveredOrdersQueue())
                .to(directExchange())
                .with(properties.deliveredOrdersQueue());
    }

    @Bean
    public Queue cancelledOrdersQueue(){
        return QueueBuilder.durable(properties.cancelledOrdersQueue()).build();
    }

    @Bean
    public Binding cancelledOrdersQueueBinding(){
        return BindingBuilder.bind(cancelledOrdersQueue())
                .to(directExchange())
                .with(properties.cancelledOrdersQueue());
    }

    @Bean
    public Queue errorOrdersQueue(){
        return QueueBuilder.durable(properties.errorOrdersQueue()).build();
    }

    @Bean
    public Binding errorOrdersQueueBinding(){
        return BindingBuilder.bind(errorOrdersQueue())
                .to(directExchange())
                .with(properties.errorOrdersQueue());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper objectMapper){
        final var rabbitTemplate =  new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(Jackson2JsonMessageConverter(objectMapper));
        rabbitTemplate.setMessageConverter(jacksonConverter(objectMapper));
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jacksonConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    // Question for you   String str = "{([])"  can you validate this?
}
