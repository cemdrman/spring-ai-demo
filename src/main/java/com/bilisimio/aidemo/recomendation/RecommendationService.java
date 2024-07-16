package com.bilisimio.aidemo.recomendation;

import com.bilisimio.aidemo.order.Order;
import com.bilisimio.aidemo.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationService {

    private final OrderRepository orderRepository;

    private final OpenAiChatModel openAiChatModel;

    public String recommend() {

        List<Order> orders = orderRepository.findAll();

        String orderHistory = orders.stream()
                .map(Order::getProductName)
                .collect(Collectors.joining(", "));

        String prompt = "I'm a software engineer. Based on my following order history: " + orderHistory
                + ", can you suggest 10 new products that I might like and explain why you recommend each of them? "
                + "Please consider my preferences and previous purchases. Please give me recommend in Turkish";

        String response = openAiChatModel.call(prompt);
        log.info(response);
        return response;
    }

}
