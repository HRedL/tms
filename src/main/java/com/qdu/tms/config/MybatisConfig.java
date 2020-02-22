package com.qdu.tms.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * mybatis的自定义配置
 */
@MapperScan(value = "com.qdu.tms.mapper") //开启mapper扫描,也可采用在每个接口上加@Mapper注解的方式
@org.springframework.context.annotation.Configuration
public class MybatisConfig {

    /**
     * 开启驼峰命名法
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
