package com.mzbloc.elasticJob.annotation;

import com.mzbloc.elasticJob.autoconfigure.JobParserAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * ElasticJob 开启注解
 *
 * <p>在Spring Boot 启动类上加此注解开启自动配置<p>
 * Created by tanxw on 2019/1/24.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({JobParserAutoConfiguration.class})
public @interface EnableElasticJob {

}
