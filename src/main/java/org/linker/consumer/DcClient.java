package org.linker.consumer;

import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient("eureka-client")
public interface DcClient {

    @GetMapping("/dc")
    String consumer();
}
