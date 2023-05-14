package com.zxcv5595.cms.user.config.Filter;

import com.zxcv5595.cms.user.service.customer.CustomerService;
import com.zxcv5595.domain.config.JwtAuthenticationProvider;
import com.zxcv5595.domain.domain.common.UserVo;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@WebFilter(urlPatterns = "/customer/*")
@RequiredArgsConstructor
public class CustomerFilter implements Filter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CustomerService customerService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("X-AUTH-TOKEN");

        if (!jwtAuthenticationProvider.validateToken(token)) {
            throw new ServletException("Invalid Access");
        }
        UserVo userVo = jwtAuthenticationProvider.getUserVo(token);
        customerService.findByIdAndEmail(userVo.getId(), userVo.getEmail()).orElseThrow(() ->
                new ServletException("Invalid Access"));
        chain.doFilter(request, response);
    }
}
