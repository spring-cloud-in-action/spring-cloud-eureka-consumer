package org.linker.consumer.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author RWM
 * @date 2018/11/28
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String findOne(Long id) {
        return restTemplate.getForObject("http://eureka-client/user/{1}", String.class, id);
    }

    @Override
    public List findAll(List<Long> ids) {
        return restTemplate.getForObject("http://eureka-client/users?ids={1}", List.class, StringUtils.join(ids, ","));
    }
}
