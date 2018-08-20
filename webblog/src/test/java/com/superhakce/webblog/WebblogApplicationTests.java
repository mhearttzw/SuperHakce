package com.superhakce.webblog;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BinaryOperator;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WebblogApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testClass(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("Anonymous Class Thread run()");
            }
        }).start();
    }

}
