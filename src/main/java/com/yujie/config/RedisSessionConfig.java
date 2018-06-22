package com.yujie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)//默认是1800秒过期，这里测试修改为60秒
public class RedisSessionConfig {
}
