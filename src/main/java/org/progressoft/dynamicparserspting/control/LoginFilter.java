//package org.progressoft.dynamicparserspting.control;
//
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//@Order(1)
//public class LoginFilter implements Filter {
//    FilterConfig config;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        config = filterConfig;
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        String path = req.getRequestURI().substring(req.getContextPath().length());
//        HttpSession session = req.getSession();
//        String username = (String) session.getAttribute("username");
//        System.out.println(path);
//        if(username == null && !path.equals("/login")){
//            servletRequest.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(servletRequest, servletResponse);
//        }
//        else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
//
//}
