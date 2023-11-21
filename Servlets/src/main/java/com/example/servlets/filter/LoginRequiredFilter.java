package com.example.servlets.filter;

import com.example.servlets.utils.page.Page;
import com.example.servlets.command.session.SessionAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebFilter(urlPatterns = "/controller")
public class LoginRequiredFilter implements Filter {
    private static final String COMMAND = "com/example/servlets/command";
    private static final String WELCOME = "welcome";
    private static final String ERROR_MESSAGE = "error_message";
    private static final String ERROR_TEXT = "Нет авторизации для выполнения данной команды";
    private static final Logger logger = LogManager.getLogger(LoginRequiredFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String command = request.getParameter(COMMAND);
        logger.info("Filter is working " + COMMAND + "= " + command);
        if (!command.equals(WELCOME)) {
            chain.doFilter(req, resp);
        } else {
            if (request.getSession().getAttribute(SessionAttribute.NAME) != null) {
                chain.doFilter(req, resp);
            } else {
                request.setAttribute(ERROR_MESSAGE, ERROR_TEXT);
                request.getRequestDispatcher(Page.ERROR_PAGE.getPage()).forward(req, resp);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
