package com.caremoa.helper.adapter;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final StreamBridge streamBridge;

    public void sendMessage(String json){
        log.info("sendMessage: {}" , json);
        streamBridge.send("basicProducer-out-0", MessageBuilder.withPayload(json)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());  
    }
}
