package org.linker.consumer.service;

import java.util.List;

/**
 * @author RWM
 * @date 2018/11/28
 */
public interface IUserService {
    String findOne(Long id);
    List<String> findAll(List<Long> ids);
}
