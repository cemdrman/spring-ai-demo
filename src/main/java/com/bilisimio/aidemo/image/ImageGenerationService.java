package com.bilisimio.aidemo.image;

import com.bilisimio.aidemo.order.Order;
import com.bilisimio.aidemo.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.ImageMessage;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageGenerationService {

    private final OpenAiImageModel openAiImageModel;
    private final OrderRepository orderRepository;

    public String generate() {

        List<Order> orders = orderRepository.findAll();

        String orderHistory = orders.stream().map(Order::getProductName).collect(Collectors.joining(", "));

        String promptWhoAmI = "I'm a software engineer.";
        String promptWhatIWear = "and i wear always basic t-shirt and sunglasses or eyeglasses.";
        String promptWhatIUse = " Based on my following order history: " + orderHistory;
        String promptWhatIWant = " Can you draw me";

        ImagePrompt imagePrompt = new ImagePrompt(List.of(new ImageMessage(promptWhoAmI),
                new ImageMessage(promptWhatIWear),
                new ImageMessage(promptWhatIUse),
                new ImageMessage(promptWhatIWant)));

        ImageResponse response = openAiImageModel.call(imagePrompt);

        log.info(response.getMetadata().toString());

        return response.getResult().getOutput().getUrl();
    }
}
