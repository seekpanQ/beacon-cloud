package com.mashibing.strategy.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@FeignClient(value = "beacon-cache")
public interface BeaconCacheClient {

    @GetMapping("/cache/hget/{key}/{field}")
    String hget(@PathVariable(value = "key") String key, @PathVariable(value = "field") String field);

    @GetMapping("/cache/get/{key}")
    String getString(@PathVariable(value = "key") String key);

    @PostMapping("/cache/sinterstr/{key}/{sinterkey}")
    Set<Object> sinterStr(@PathVariable(value = "key") String key,
                          @PathVariable(value = "sinterkey") String sinterKey,
                          @RequestBody String... value);

    @GetMapping("/cache/smembers/{key}")
    Set<String> smembers(@PathVariable(value = "key")String key);
}
