package br.com.ebn.springclouddemo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class DemoPosFilter extends AbstractGatewayFilterFactory<DemoPosFilter.Config> {

    public DemoPosFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        log.info("Pos filter apply");
        return (exchange, chain) -> chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    ServerHttpResponse response = exchange.getResponse();
                    HttpHeaders headers = response.getHeaders();
                    headers.forEach((k,v) -> log.info(k + " : " + v));
                }));
    }

    public static class Config{
        private String name;

        public String getName(){ return  this.name; }
        public void setName(String name){ this.name = name; }
    }
}
