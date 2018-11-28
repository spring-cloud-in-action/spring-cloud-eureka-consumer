package org.linker.consumer.controller;

import org.apache.commons.lang.StringUtils;
import org.linker.consumer.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author RWM
 * @date 2018/11/28
 */
@RestController
public class UserApiController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/findOne/{id}")
    public String findOne(@PathVariable Long id) {
        return iUserService.findOne(id);
    }

    @GetMapping("/findAll")
    public List<String> findALl(@RequestParam String ids) {
        List<String> idsStr = Arrays.asList(StringUtils.split(ids, ","));
        return iUserService.findAll(idsStr.stream().map(Long::parseLong).collect(Collectors.toList()));
    }
}
