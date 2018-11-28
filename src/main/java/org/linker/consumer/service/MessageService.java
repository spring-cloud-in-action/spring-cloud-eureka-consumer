package org.linker.consumer.service;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.linker.consumer.collapser.UserCollapseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author RWM
 * @date 2018/11/28
 */
@Service
public class MessageService {


    @Autowired
    private IUserService iUserService;

    public void sendMessage() {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            StringBuilder result = new StringBuilder();
            Future<String> future1 = new UserCollapseCommand(iUserService, 1L).queue();
            Future<String> future2 = new UserCollapseCommand(iUserService, 2L).queue();
            Future<String> future3 = new UserCollapseCommand(iUserService, 3L).queue();
            result.append(future1.get());
            result.append(future2.get());
            result.append(future3.get());
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            context.close();
        }
    }

}
