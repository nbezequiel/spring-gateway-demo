package br.com.ebn.springclouddemo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DemoPreFilter extends AbstractGatewayFilterFactory<DemoPreFilter.Config> {

    public DemoPreFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        log.info("Pre filter apply");
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest()
                    .mutate()
                    .header("pre-header", Math.random() * 10 + "-pre")
                    .build();

            return chain.filter(
                    exchange.mutate()
                            .request(request)
                            .build());
        };
    }

    public static class Config{
        private String name;

        public String getName(){ return  this.name; }
        public void setName(String name){ this.name = name; }
    }
}
