package com.sales.market.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

import static com.sales.market.service.TokenService.UNAUTHORIZED_USER_MESSAGE;

@Component
public class JwtUnauthorizedHandler implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -2812636648800897280L;

    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, UNAUTHORIZED_USER_MESSAGE);
    }
}
