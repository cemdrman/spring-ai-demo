package com.bilisimio.aidemo.image;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageGenerationService imageGenerationService;

    @GetMapping
    public String imageGenerate(){
        return imageGenerationService.generate();
    }
}
