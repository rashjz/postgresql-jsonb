package rashjz.info.app.postgresql.config;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import rashjz.info.app.postgresql.dialect.PostgreSQL95JsonDialect;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class PersistenceConfig {

    /**
     * An Internal Hibernate Property used for suppressing the cLOB stack-traces
     *
     * From the Hibernate Configuration Documentation
     * <p>
     *     This setting is used to control whether we should consult the JDBC metadata to determine
     *     certain Settings default values when the database may not be available (mainly in tools usage).
     * </p>
     */
    private static final String USE_JDBC_METADATA_DEFAULTS = "hibernate.temp.use_jdbc_metadata_defaults";


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("rashjz.info.app.postgresql.model.entities");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty(AvailableSettings.DIALECT, PostgreSQL95JsonDialect.class.getCanonicalName());
        properties.setProperty(AvailableSettings.PHYSICAL_NAMING_STRATEGY, SpringPhysicalNamingStrategy.class.getCanonicalName());
        properties.setProperty(USE_JDBC_METADATA_DEFAULTS, "false");
        em.setJpaProperties(properties);

        return em;
    }
}
