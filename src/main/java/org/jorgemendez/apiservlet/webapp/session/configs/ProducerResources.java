package org.jorgemendez.apiservlet.webapp.session.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
@ApplicationScoped
public class ProducerResources {
    @Resource(name = "jdbc/mysqlDB")
    private DataSource ds;
    @Produces
    @RequestScoped
    @MysqlConnPrincipal
    //@Named("conn")
    private Connection beanConnection() throws SQLException, NamingException {
        //Context initContext = null;
       // initContext = new InitialContext();
       // Context envContext = (Context) initContext.lookup("java:/comp/env");
       // DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");

        return ds.getConnection();
    }

    public void close(@Disposes @MysqlConnPrincipal Connection connection) throws SQLException {
        connection.close();
    }
}
