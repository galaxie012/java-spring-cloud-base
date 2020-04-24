package hou.tidaa.gateway.filters.jwt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMessage;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JWTGatewayFilterFactoryTest {
    @Mock
    HttpMessage httpMessage;

    @InjectMocks
    JWTGatewayFilterFactory jwtFilter;

    @Test
    void authorization_header_is_missing() {
        Mockito.when(httpMessage.getHeaders())
                .thenReturn(new HttpHeaders());

        Exception exception = assertThrows(JWTException.class, () ->{
            jwtFilter.extractJWTToken(httpMessage);
        });
    }
}
