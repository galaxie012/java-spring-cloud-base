package hou.tidaa.gateway.filters.jwt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMessage;

import java.util.ArrayList;

import static hou.tidaa.gateway.filters.jwt.JWTException.AUTHORIZATION_CONTENT_IS_MISSING;
import static hou.tidaa.gateway.filters.jwt.JWTException.AUTHORIZATION_HEADER_IS_MISSING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

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

        assertEquals(AUTHORIZATION_HEADER_IS_MISSING, exception.getMessage());
    }

    @Test
    void authorization_content_is_missing() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put(AUTHORIZATION, new ArrayList<>());

        Mockito.when(httpMessage.getHeaders())
                .thenReturn(httpHeaders);

        Exception exception = assertThrows(JWTException.class, () -> {
            jwtFilter.extractJWTToken(httpMessage);
        });

        assertEquals(AUTHORIZATION_CONTENT_IS_MISSING, exception.getMessage());
    }
}
