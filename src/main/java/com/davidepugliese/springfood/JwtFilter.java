package com.davidepugliese.springfood;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtFilter extends GenericFilterBean {
    @Value("${jwt.secret}")
    private String secret;
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("authorization");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);

            chain.doFilter(req, res);
        } else {

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                //pass down the actual obj that exception handler normally send
                ObjectMapper mapper = new ObjectMapper();
                PrintWriter out = res.getWriter();
                out.print(mapper.writeValueAsString("Missing or invalid Authorization header"));
                out.flush();
                return;

            }

            final String token = authHeader.substring(7);

            try {
                System.out.println(this.secret);
                final Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
                request.setAttribute("claims", claims);
            } catch (final SignatureException e) {
                ObjectMapper mapper = new ObjectMapper();
                PrintWriter out = res.getWriter();
                out.print(mapper.writeValueAsString("Invalid token" ));
                out.flush();
                return;

            }

            chain.doFilter(req, res);
        }
    }
}
