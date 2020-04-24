package hou.tidaa.gateway.filters.jwt;

import hou.tidaa.core.security.jwt.JWTVerifyService;
import hou.tidaa.core.security.token.IdentityDto;
import hou.tidaa.core.security.token.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

import static hou.tidaa.gateway.filters.jwt.JWTException.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Component
public class JWTGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    public static final String JWT_PREFIX = "Bearer";
    public static final String ID_HEADER = "X-identity";

    private final JWTVerifyService verifyService;


    @Autowired
    public JWTGatewayFilterFactory(JWTVerifyService verifyService) {
        this.verifyService = verifyService;
    }

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            try{
                Token<String> token = this.extractJWTToken(exchange.getRequest());
                IdentityDto identityDto = this.verifyService.verify(token)
                        .orElseThrow(() -> new JWTException(MALFORMED_AUTHORIZATION_CONTENT));

                ServerHttpRequest request = exchange.getRequest().mutate().
                        header(ID_HEADER, identityDto.getUid()).
                        build();
                return chain.filter(exchange.mutate().request(request).build());
            }catch (JWTException ex) {
                return this.onError(exchange, ex.getMessage());
            }
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(AUTHORIZATION, this.formatErrorMsg(err));

        return response.setComplete();
    }

    public Token<String> extractJWTToken(HttpMessage request) throws JWTException {
        if (!request.getHeaders().containsKey(AUTHORIZATION)) {
            throw new JWTException(AUTHORIZATION_HEADER_IS_MISSING);
        }

        List<String> headers = request.getHeaders().get(AUTHORIZATION);
        if (headers.isEmpty()) {
            throw new JWTException(AUTHORIZATION_CONTENT_IS_MISSING);
        }

        String credential = headers.get(0).trim();
        String[] components = credential.split("\\s");

        if (components.length != 2) {
            throw new JWTException(MALFORMED_AUTHORIZATION_CONTENT);
        }

        if (!components[0].equals(JWT_PREFIX)) {
            throw new JWTException(BEARER_IS_NEEDED);
        }

        return Token.jwt(components[1].trim());
    }

    private String formatErrorMsg(String msg) {
        return String.format("Bearer realm=\"localhost\", " +
                "error=\"https://tools.ietf.org/html/rfc7519\", " +
                "error_description=\"%s\" ", msg);
    }
}
