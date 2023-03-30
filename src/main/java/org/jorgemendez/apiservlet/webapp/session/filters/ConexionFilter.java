package org.jorgemendez.apiservlet.webapp.session.filters;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.jorgemendez.apiservlet.webapp.session.configs.MysqlConnPrincipal;
import org.jorgemendez.apiservlet.webapp.session.service.ServiceJdbcException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Inject
    @MysqlConnPrincipal
    private  Connection conn;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            Connection conn = this.conn;
            if (conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try{
                filterChain.doFilter(servletRequest,servletResponse);
                conn.commit();
            }catch (SQLException | ServiceJdbcException  e){
                conn.rollback();
                ((HttpServletResponse)(servletResponse)).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
