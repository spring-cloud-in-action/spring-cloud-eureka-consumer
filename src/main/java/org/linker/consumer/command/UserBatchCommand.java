package org.linker.consumer.command;


import com.netflix.hystrix.HystrixCommand;
import org.linker.consumer.service.IUserService;

import java.util.List;

import static com.netflix.hystrix.HystrixCommandGroupKey.Factory.asKey;

/**
 * @author RWM
 * @date 2018/11/27
 */
public class UserBatchCommand extends HystrixCommand<List<String>> {

    IUserService userService;
    List<Long> ids;

    public UserBatchCommand(IUserService userService, List<Long> ids) {
        super(Setter.withGroupKey(asKey("userServiceCommand")));
        this.userService = userService;
        this.ids = ids;
    }

    @Override
    protected List<String> run() throws Exception {
        return userService.findAll(ids);
    }
}
