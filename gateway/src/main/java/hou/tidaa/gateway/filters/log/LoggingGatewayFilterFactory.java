package hou.tidaa.gateway.filters.log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class LoggingGatewayFilterFactory extends
        AbstractGatewayFilterFactory<LoggingGatewayFilterFactory.Config> {

    public static final String BASE_MSG = "baseMessage";
    public static final String PRE_LOGGER = "preLogger";
    public static final String POST_LOGGER = "postLogger";

    public LoggingGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(BASE_MSG, PRE_LOGGER, POST_LOGGER);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Pre-processing
            if (config.isPreLogger()) {
                log.info("Pre GatewayFilter logging: "
                        + config.getBaseMessage());
            }
            log.info("enter LoggingGatewayFilter");
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        // Post-processing
                        if (config.isPostLogger()) {
                            log.info("Post GatewayFilter logging: "
                                    + config.getBaseMessage());
                        }
                    }));
        };
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Config {
        private String baseMessage;
        private boolean preLogger = true;
        private boolean postLogger = true;
    }
}
