package hou.tidaa.gateway.filters.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;

@Slf4j
public class AddRequestHeaderGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(AbstractNameValueGatewayFilterFactory.NameValueConfig config) {
        return (exchange, chain) -> {
            log.info("enter AddRequestHeaderGatewayFilter");
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header(config.getName(), config.getValue())
                    .build();

            return chain.filter(exchange.mutate().request(request).build());
        };
    }

}
