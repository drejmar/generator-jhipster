package <%=packageName%>.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;<% if (databaseType == 'nosql') { %>
import org.springframework.data.mongodb.MongoDbFactory;<% } %>
<% if (databaseType == 'sql') { %>
import javax.sql.DataSource;<% } %>

@Profile("cloud")
@Configuration
public class CloudDatabaseConfiguration extends AbstractCloudConfig {

    private final Logger log = LoggerFactory.getLogger(CloudDatabaseConfiguration.class);<% if (databaseType == 'sql') { %>

    @Bean
    public DataSource dataSource() {
        log.info("Configuring JDBC datasource from a cloud provider");
        return connectionFactory().dataSource();
    }<% } %><% if (databaseType == 'nosql') { %>

    @Bean
    public MongoDbFactory mongoDbFactory() {
        log.info("Configuring MongoDB datasource from a cloud provider");
        return connectionFactory().mongoDbFactory();
    }<% } %>
}
