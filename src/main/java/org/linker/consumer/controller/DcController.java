package org.linker.consumer.controller;

import org.linker.consumer.feign.DcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RWM
 * @date 2018/8/20
 */
@RestController
public class DcController {

    @Autowired
    private DcClient dcClient;

    /*@Autowired
    private RestTemplate restTemplate;*/

    @GetMapping("/consumer")
    public String consumer() {
        return dcClient.dc();
    }

    /*@GetMapping("/dc")
    public String dc() {
        return restTemplate.getForObject("http://eureka-client/dc/{1}", String.class, 0);
    }

    @GetMapping("/dcs")
    public List dcs() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        return restTemplate.getForObject("http://eureka-client/dc?ids={1}", List.class, StringUtils.join(ids, ","));
    }*/

}
