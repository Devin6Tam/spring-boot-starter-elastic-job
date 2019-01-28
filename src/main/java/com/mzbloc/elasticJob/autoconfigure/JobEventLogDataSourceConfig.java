package com.mzbloc.elasticJob.autoconfigure;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by tanxw on 2019/1/28.
 */
@Configuration
public class JobEventLogDataSourceConfig {

    /**
     * 任务执行事件数据源
     * @return
     */
    @Bean(name="jobEventLogDatasource")
    @ConfigurationProperties("spring.datasource.druid.log")
    public DataSource jobEventLogDatasource(){
        return DruidDataSourceBuilder.create().build();
    }
}
