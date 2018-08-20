package com.superhakce.webblog;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 测试
 * @Date: Create in 2018/8/20 15:51
 */
@RestController
@RequestMapping("/api/webblog/test")
public class TestController {

    @GetMapping("/flux")
    public Mono<String> flux() {
        return Mono.just("Hello Flux");
    }

    @GetMapping("randomNumber")
    public Flux<ServerSentEvent<Integer>> randomNumber(){
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> Tuples.of(seq , ThreadLocalRandom.current().nextInt()))
                .map(data -> ServerSentEvent.<Integer>builder()
                        .event("random")
                        .id(Long.toString(data.getT1()))
                        .data(data.getT2())
                        .build());

    }

}
