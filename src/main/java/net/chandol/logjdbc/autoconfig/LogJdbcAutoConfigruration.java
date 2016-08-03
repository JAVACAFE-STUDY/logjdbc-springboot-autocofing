package net.chandol.logjdbc.autoconfig;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@EnableConfigurationProperties
@ConditionalOnProperty(prefix = "logjdbc", name = "enabled", havingValue = "true")
public class LogJdbcAutoConfigruration {
    private static final Logger logger = LoggerFactory.getLogger(LogJdbcAutoConfigruration.class);

    @PostConstruct
    public void setLog4jdbcOption(){
        logger.debug("logjdbc is enabled");
    }

    // TODO logback일때, log4j일때 로깅 설정에 따른 설정 분리 필요.
    // 우선은 logback을 기준으로 처리하자!
    public void afterPropertiesSet() throws Exception {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    }


    public static class InstantiationTracingBeanPostProcessor implements BeanPostProcessor {

        // 간단히 현상태의 인스턴스화 된 빈을 리턴한다
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            return bean; // 여기서 잠재적으로 어떤 객체의 참조도 리턴할 수 있다...
        }

        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            System.out.println("Bean '" + beanName + "' created : " + bean.toString());

            return bean;
        }
    }


}

