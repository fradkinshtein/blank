package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest ? useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rooot";
    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;
    //https://www.sourcecodeexamples.net/2019/11/hibernate-java-configuration-without-xml-example-in-eclipse-using-maven.html
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USERNAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
   //Connection establishment for JDBS
    private Connection conn = null;

    public static Connection getMySQLConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException throwables) {

        }
        return conn;

    }
}
//Connection establishment for Hibernate пробы пера
//   static {
//        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
//        Map<String, String> dbSettings = new HashMap<>();
//        dbSettings.put(Environment.URL, URL);
//        dbSettings.put(Environment.USER, USERNAME);
//        dbSettings.put(Environment.PASS, PASSWORD);
//        dbSettings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//        dbSettings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
//        registryBuilder.applySettings(dbSettings);
//        standardServiceRegistry = registryBuilder.build();
//        MetadataSources sources = new MetadataSources(standardServiceRegistry);
//        Metadata metadata = sources.getMetadataBuilder().build();
//        sessionFactory = metadata.getSessionFactoryBuilder().build();
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
//        properties.setProperty("hibernate.connection.url", URL);
//        properties.setProperty("hibernate.connection.username", USERNAME);
//        properties.setProperty("hibernate.connection.password", PASSWORD);
//        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
//       // properties.setProperty("hibernate.hbm2ddl.auto", "create");
//
//        Configuration configuration = new Configuration()
//                .addProperties(properties);//.addAnnotatedClass(User.class);
//        StandardServiceRegistryBuilder builder =
//                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//        sessionFactory = configuration.buildSessionFactory(builder.build());
//    }
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }