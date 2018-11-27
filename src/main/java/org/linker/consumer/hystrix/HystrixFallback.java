package org.linker.consumer.hystrix;

import org.linker.consumer.feign.DcClient;
import org.springframework.stereotype.Service;

/**
 * @author RWM
 * @date 2018/11/27
 */
@Service
public class HystrixFallback implements DcClient {
    @Override
    public String dc() {
        return "Fallback!";
    }
}
