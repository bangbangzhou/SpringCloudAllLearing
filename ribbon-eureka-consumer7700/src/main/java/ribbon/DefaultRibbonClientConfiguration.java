package ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-12 00:43
 **/
@Configuration
public class DefaultRibbonClientConfiguration {

    @Bean
    public IRule ribbonDefaultRule() {
        return new RoundRobinRule();
    }

}
