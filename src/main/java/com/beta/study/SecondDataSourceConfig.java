package com.beta.study;


import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @date 2017/03/27
 */
@Configuration
@PropertySource("classpath:datasource.properties")
@MapperScan(basePackages =  SecondDataSourceConfig.SCAN_PACKAGE,sqlSessionFactoryRef = "secondSqlSessionFactory")
public class SecondDataSourceConfig {

    final static String SCAN_PACKAGE = "com.beta.study.mapper.secondjdbc";

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.maxActive}")
    private int maxActive;
    @Value("${jdbc.maxIdel}")
    private int maxIdel;

    @Bean(name="secondDataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxConnLifetimeMillis(maxActive);
        dataSource.setMaxIdle(maxIdel);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(true);
        return dataSource;
    }

    @Bean(name = "secondTransactionManager")
    public DataSourceTransactionManager defaultDataSourceTransactionManager() {
        return new DataSourceTransactionManager(getDataSource());
    }

    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory defaultSqlSessionFactory(@Qualifier("secondDataSource")DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }
}
