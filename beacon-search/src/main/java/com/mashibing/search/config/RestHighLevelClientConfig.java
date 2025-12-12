package com.mashibing.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RestHighLevelClientConfig {
    @Value("#{'${elasticsearch.hostAndPorts}'.split(',')}")
    private List<String> hostAndPorts;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        // 初始化连接ES的HttpHost信息
        HttpHost[] httpHosts = new HttpHost[hostAndPorts.size()];

        for (int i = 0; i < hostAndPorts.size(); i++) {
            String[] hostAndPort = hostAndPorts.get(i).split(":");
            httpHosts[i] = new HttpHost(hostAndPort[0], Integer.parseInt(hostAndPort[1]));
        }

        RestClientBuilder restClientBuilder = RestClient.builder(httpHosts);

        // 构建连接ES的client对象
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        return restHighLevelClient;
    }
}
