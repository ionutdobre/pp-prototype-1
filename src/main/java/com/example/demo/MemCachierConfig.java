package com.example.demo;

import com.google.code.ssm.Cache;
import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.config.AbstractSSMConfiguration;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl;
import com.google.code.ssm.providers.xmemcached.XMemcachedConfiguration;
import com.google.code.ssm.spring.ExtendedSSMCacheManager;
import com.google.code.ssm.spring.SSMCache;
import net.rubyeye.xmemcached.auth.AuthInfo;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class MemCachierConfig extends AbstractSSMConfiguration {

    @Bean
    @Override
    public CacheFactory defaultMemcachedClient() {
        final String serverString = System.getenv("MEMCACHIER_SERVERS").replace(",", " ");
        final List<InetSocketAddress> servers = AddrUtil.getAddresses(serverString);
        final AuthInfo authInfo = AuthInfo.plain(System.getenv("MEMCACHIER_USERNAME"),
                System.getenv("MEMCACHIER_PASSWORD"));
        final Map<InetSocketAddress, AuthInfo> authInfoMap = new HashMap<>();

        for (final InetSocketAddress server : servers) {
            authInfoMap.put(server, authInfo);
        }

        final XMemcachedConfiguration conf = new XMemcachedConfiguration();
        conf.setUseBinaryProtocol(true);
        conf.setAuthInfoMap(authInfoMap);

        final CacheFactory cf = new CacheFactory();
        cf.setCacheClientFactory(new MemcacheClientFactoryImpl());
        cf.setAddressProvider(new DefaultAddressProvider(serverString));
        cf.setConfiguration(conf);
        return cf;
    }

    @Bean
    public CacheManager cacheManager() throws Exception {
        // Use SSMCacheManager instead of ExtendedSSMCacheManager if you do not need to set per key expiration
        final ExtendedSSMCacheManager cacheManager = new ExtendedSSMCacheManager();
        final Cache cache = this.defaultMemcachedClient().getObject();
        // SSMCache(cache, 0, false) creates a cache with default key expiration
        // of 0 (no expiration) and flushing disabled (allowClear = false)
        cacheManager.setCaches(Arrays.asList(new SSMCache(cache, 0, false)));

        return cacheManager;
    }
}
