package lbrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 必须写到ConmentScaner扫描之外：官网说的
 */
@SpringBootConfiguration
public class LoadBalance {

    //随机算法 负载均衡
    @Bean
    public IRule RandomLoadBalance(){
        return new RandomRule();
    }
//    //轮询算法 负载均衡
//    @Bean
//    public IRule RoundLoadBalance(){
//        return new RoundRobinRule();
//    }
//    //重试算法 负载均衡
//    @Bean
//    public IRule RetryLoadBalance(){
//        return new RetryRule();
//    }

}
