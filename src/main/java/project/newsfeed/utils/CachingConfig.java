package project.newsfeed.utils;

import com.google.common.cache.CacheLoader;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static com.google.common.cache.CacheBuilder.newBuilder;
import static java.util.concurrent.TimeUnit.MINUTES;


@Configuration
@EnableCaching
public class CachingConfig {

    @Primary
    @Bean
    public CacheManager defaultCache() {
        return new ConcurrentMapCacheManager();
    }

//    @Bean
//    public CacheManager cacheWithTTL() {
//        return new ConcurrentMapCacheManager() {
//            @Override
//            protected Cache createConcurrentMapCache(String name) {
//
//                ConcurrentMapCache concurrentMapCache = new ConcurrentMapCache(name,
//                        newBuilder().expireAfterWrite(60, MINUTES).build().asMap(), isAllowNullValues());
//                return concurrentMapCache;
//            }
//        };
//    }

}
