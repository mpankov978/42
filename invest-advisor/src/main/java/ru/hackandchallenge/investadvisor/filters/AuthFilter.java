package ru.hackandchallenge.investadvisor.filters;

import lombok.AllArgsConstructor;
import ru.hackandchallenge.investadvisor.dto.auth.CheckAuthResponse;
import ru.hackandchallenge.investadvisor.services.CheckAuthService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@AllArgsConstructor
public class AuthFilter implements Filter {

    private static final String AUTH_HEADER = "authorization";
    private CheckAuthService checkAuthService;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String jwt = req.getHeader(AUTH_HEADER);
        CheckAuthResponse checkAuthResponse = checkAuthService.checkAuth(jwt);
        req.setAttribute("clientId", checkAuthResponse.getUserId());
        chain.doFilter(request, response);
    }
}