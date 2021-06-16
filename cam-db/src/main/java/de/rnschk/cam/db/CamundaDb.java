package de.rnschk.cam.db;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * default jdbc url:
 * jdbc:h2:tcp://localhost:9092/mem:camunda
 * <p>
 * default console url:
 * http://localhost:9091/h2-console
 */
@SpringBootApplication
public class CamundaDb {

    @Value("${de.rnschk.h2server.jdbcport:9092}")
    private String port;

    @Value("${de.rnschk.h2server.dbname:camunda}")
    private String dbname;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpPort", port);
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                                            .setName(dbname)
                                            .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(CamundaDb.class, args);
    }
}
