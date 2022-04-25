package com.tencent.tsf.femas.storage.external;

import com.tencent.tsf.femas.exception.FemasException;
import com.tencent.tsf.femas.storage.external.rowmapper.AuthRowMapper;
import com.tencent.tsf.femas.storage.external.rowmapper.CircuitBreakerRowMapper;
import com.tencent.tsf.femas.storage.external.rowmapper.NamespaceRowMapper;
import com.tencent.tsf.femas.storage.external.rowmapper.RateLimitRowMapper;
import com.tencent.tsf.femas.storage.external.rowmapper.RouteRowMapper;
import com.tencent.tsf.femas.storage.external.rowmapper.ServiceEventRowMapper;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * @author Cody
 * @date 2021 2021/8/4 10:39 上午
 */
@Component
public class RowMapperFactory {

    private static HashMap<MapperType, RowMapper> mapperContext = new HashMap<>();

    public static RowMapper getMapper(MapperType mapperType) {
        RowMapper rowMapper = mapperContext.get(mapperType);
        if (rowMapper == null) {
            throw new FemasException("mapper is empty");
        }
        return rowMapper;
    }

    @PostConstruct
    public void init() {
        AuthRowMapper authRowMapper = new AuthRowMapper();
        CircuitBreakerRowMapper circuitBreakerRowMapper = new CircuitBreakerRowMapper();
        RateLimitRowMapper rateLimitRowMapper = new RateLimitRowMapper();
        RouteRowMapper routeRowMapper = new RouteRowMapper();
        ServiceEventRowMapper serviceEventRowMapper = new ServiceEventRowMapper();
        NamespaceRowMapper namespaceRowMapper = new NamespaceRowMapper();
        mapperContext.put(MapperType.AUTH, authRowMapper);
        mapperContext.put(MapperType.CIRCUIT_BREAKER, circuitBreakerRowMapper);
        mapperContext.put(MapperType.RATE_LIMIT, rateLimitRowMapper);
        mapperContext.put(MapperType.ROUTE, routeRowMapper);
        mapperContext.put(MapperType.SERVICE_EVENT, serviceEventRowMapper);
        mapperContext.put(MapperType.NAMESPACE, namespaceRowMapper);
    }


    public enum MapperType {
        AUTH,
        ROUTE,
        CIRCUIT_BREAKER,
        RATE_LIMIT,
        SERVICE_EVENT,
        NAMESPACE,
    }
}
