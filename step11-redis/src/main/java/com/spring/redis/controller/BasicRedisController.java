package com.spring.redis.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.redis.service.BasicRedisService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/redis/basic")
@RequiredArgsConstructor
public class BasicRedisController {
    
    private final BasicRedisService basicRedisService;
//    private final CounterService counterService;    
    @PostMapping("/string")
    public ResponseEntity<String> setString(@RequestParam String key, 
                                          @RequestParam String value) {
        basicRedisService.setString(key, value);
        return ResponseEntity.ok("Saved");
    }
    
    @GetMapping("/string/{key}")
    public ResponseEntity<String> getString(@PathVariable String key) {
        return ResponseEntity.ok(basicRedisService.getString(key));
    }
    
    @PostMapping("/ttl")
    public ResponseEntity<String> setWithTTL(@RequestParam String key,
                                           @RequestParam String value,
                                           @RequestParam long seconds) {
        basicRedisService.setWithTTL(key, value, seconds, TimeUnit.SECONDS);
        return ResponseEntity.ok("Saved with TTL: " + seconds + " seconds");
    }
    
//    @PostMapping("/counter/{key}/increment")
//    public ResponseEntity<Long> increment(@PathVariable String key) {
//        return ResponseEntity.ok(counterService.increment(key));
//    }
//    
//    @GetMapping("/view/{contentId}")
//    public ResponseEntity<Long> getViewCount(@PathVariable String contentId) {
//        return ResponseEntity.ok(counterService.incrementViewCount(contentId));
//    }
}
