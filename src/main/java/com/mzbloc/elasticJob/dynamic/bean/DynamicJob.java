package com.mzbloc.elasticJob.dynamic.bean;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.Data;

/**
 * Created by tanxw on 2019/1/28.
 */
@Data
public class DynamicJob implements ElasticJob{
    /**
     * 数据ID 可为空
     */
    private String id;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务执行时间配置
     */
    private String cron;

    /**
     * 任务分片总数
     */
    private Integer shardingTotalCount;

    /**
     * 任务分片项目参数 可为空
     */
    private String shardingItemParameters;

    /**
     * 动态任务实现类
     */
    private Class<? extends SimpleJob> jobImplClass;
}
