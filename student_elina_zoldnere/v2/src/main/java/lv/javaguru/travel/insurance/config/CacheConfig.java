package lv.javaguru.travel.insurance.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
class CacheConfig {

    @Value("${cache.profile.1.initial.capacity}")
    private int profile1InitialCapacity;
    @Value("${cache.profile.1.max.size}")
    private int profile1MaxSize;
    @Value("${cache.profile.1.expires.after.access}")
    private int profile1Expiration;
    @Value("${cache.profile.2.initial.capacity}")
    private int profile2InitialCapacity;
    @Value("${cache.profile.2.max.size}")
    private int profile2MaxSize;
    @Value("${cache.profile.2.expires.after.access}")
    private int profile2Expiration;

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        cacheManager.registerCustomCache("classifierCache", buildCaffeineProfile1());
        cacheManager.registerCustomCache("classifierValueCache", buildCaffeineProfile2());
        cacheManager.registerCustomCache("tcTravelCostCoefficientCache", buildCaffeineProfile1());
        cacheManager.registerCustomCache("tcCountrySafetyRatingCoefficientCache", buildCaffeineProfile1());
        cacheManager.registerCustomCache("tcAgeCoefficientCache", buildCaffeineProfile1());
        cacheManager.registerCustomCache("tcAgeCoefficientCache", buildCaffeineProfile1());
        cacheManager.registerCustomCache("countryDefaultDayRateCache", buildCaffeineProfile2());
        cacheManager.registerCustomCache("medicalRiskLimitLevelCache", buildCaffeineProfile1());
        cacheManager.registerCustomCache("tmAgeCoefficientCache", buildCaffeineProfile1());
        return cacheManager;
    }

    private Cache<Object, Object> buildCaffeineProfile1() {
        return Caffeine.newBuilder()
                .initialCapacity(profile1InitialCapacity)
                .maximumSize(profile1MaxSize)
                .expireAfterAccess(profile1Expiration, TimeUnit.MINUTES)
                .build();
    }

    private Cache<Object, Object> buildCaffeineProfile2() {
        return Caffeine.newBuilder()
                .initialCapacity(profile2InitialCapacity)
                .maximumSize(profile2MaxSize)
                .expireAfterAccess(profile2Expiration, TimeUnit.MINUTES)
                .build();
    }

}