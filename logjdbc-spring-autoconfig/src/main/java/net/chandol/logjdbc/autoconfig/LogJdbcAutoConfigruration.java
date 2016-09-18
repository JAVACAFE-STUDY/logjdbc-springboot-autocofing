package net.chandol.logjdbc.autoconfig;

import net.chandol.logjdbc.LogJdbcDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
@ConditionalOnProperty(prefix = "logjdbc", name = "enabled", havingValue = "true")
public class LogJdbcAutoConfigruration {
    private static final Logger logger = LoggerFactory.getLogger(LogJdbcAutoConfigruration.class);

    @PostConstruct
    public void test(){
        System.out.println("여기 호출되요1!");
        System.out.println("여기 호출되요1!");
        System.out.println("여기 호출되요1!");
        System.out.println("여기 호출되요1!");
        System.out.println("여기 호출되요1!");
        System.out.println("여기 호출되요1!");
    }

    @Bean
    public BeanPostProcessor datasourceBeanPostProcessor() {
        return new BeanPostProcessor() {
            public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
                return bean;
            }

            public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
                if (DataSource.class.isInstance(bean)) {
                    logger.info("logjdbc가 활성화 되었습니다.");
                    return new LogJdbcDataSource((DataSource) bean);
                } else {
                    return bean;
                }
            }
        };
    }
}
