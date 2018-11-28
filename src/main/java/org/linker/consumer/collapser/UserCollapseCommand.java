package org.linker.consumer.collapser;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import org.linker.consumer.command.UserBatchCommand;
import org.linker.consumer.service.IUserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author RWM
 * @date 2018/11/27
 */
public class UserCollapseCommand extends HystrixCollapser<List<String>, String, Long> {

    private IUserService userService;
    private Long id;

    public UserCollapseCommand(IUserService userService, Long id) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("userCollapseCommand")).andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(100)));
        this.userService = userService;
        this.id = id;
    }

    @Override
    public Long getRequestArgument() {
        return id;
    }

    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, Long>> collection) {
        List<Long> ids = new ArrayList<>(collection.size());
        ids.addAll(collection.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
        return new UserBatchCommand(userService, ids);
    }

    @Override
    protected void mapResponseToRequests(List<String> strings, Collection<CollapsedRequest<String, Long>> collection) {
        int count = 0;
        for (CollapsedRequest<String, Long> request : collection) {
            String response = strings.get(count++);
            request.setResponse(response);
        }
    }
}
