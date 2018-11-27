package org.linker.consumer.feign;

import org.linker.consumer.hystrix.HystrixFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "eureka-client", fallback = HystrixFallback.class)
public interface DcClient {

    @GetMapping("/dc")
    String dc();
}
