package com.bilisimio.aidemo.recomendation;

import com.bilisimio.aidemo.order.Order;
import com.bilisimio.aidemo.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationService {

    private final OrderRepository orderRepository;
    private final ChatModel chatModel;

    public String recommend() {
        List<Order> orders = orderRepository.findAll();

        String orderHistory = orders.stream()
                .map(Order::getProductName)
                .collect(Collectors.joining(", "));

        String prompt = "Based on the following order history: " + orderHistory + ", suggest some products.";

        ChatResponse response = chatModel.call(new Prompt(prompt,
                OpenAiChatOptions.builder()
                        .withModel("gpt-4.o")
                        .build()));
        log.info(response.toString());
        return response.getResult().getOutput().toString();
    }


}
