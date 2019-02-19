package com.mzbloc.elasticJob.handler;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.mzbloc.elasticJob.dynamic.bean.DynamicJob;
import com.mzbloc.elasticJob.dynamic.bean.MyJob;
import com.mzbloc.elasticJob.listener.ElasticJobListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by tanxw on 2019/1/28.
 */
@Component
public class ElasticJobHandler {
    @Autowired
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    @Autowired
    private ElasticJobListener elasticJobListener;

    /**
     * @param jobName
     * @param jobClass
     * @param shardingTotalCount
     * @param cron
     * @param id                 数据ID
     * @return
     */
    private static LiteJobConfiguration.Builder simpleJobConfigBuilder(String jobName,
                                                                       Class<? extends SimpleJob> jobClass,
                                                                       int shardingTotalCount,
                                                                       String cron,
                                                                       String id, String parameters) {
        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(
                JobCoreConfiguration
                        .newBuilder(jobName, cron, shardingTotalCount)
                        .shardingItemParameters(parameters)
                        .jobParameter(id).
                        build(),
                jobClass.getCanonicalName()));
    }

    /**
     * 添加一个定时任务
     *
     * @param jobName            任务名
     * @param jobImplClass       任务实现类
     * @param cron               表达式
     * @param shardingTotalCount 分片数
     * @param parameters         当前参数
     */
    public void addJob(String jobName,Class<? extends SimpleJob> jobImplClass, String cron, Integer shardingTotalCount, String id,String parameters) {
        LiteJobConfiguration jobConfig = simpleJobConfigBuilder(jobName, jobImplClass, shardingTotalCount, cron, id,parameters)
                .overwrite(true).build();

        new SpringJobScheduler(new MyJob(), zookeeperRegistryCenter, jobConfig, elasticJobListener).init();
    }

    /**
     * 添加一个定时任务
     *
     * @param dynamicJob   动态任务对象
     */
    public void addJob(DynamicJob dynamicJob) {
        addJob(dynamicJob.getJobName(),dynamicJob.getJobImplClass(),
                dynamicJob.getCron(), dynamicJob.getShardingTotalCount(),
                dynamicJob.getId(),dynamicJob.getShardingItemParameters());
    }
}
