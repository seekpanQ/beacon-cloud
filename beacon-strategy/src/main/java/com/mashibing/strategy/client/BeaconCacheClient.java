package com.mashibing.strategy.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
    Set<String> smembers(@PathVariable(value = "key") String key);

    @GetMapping("/cache/hget/{key}/{field}")
    Integer hgetInteger(@PathVariable(value = "key") String key, @PathVariable(value = "field") String field);

    @PostMapping("/cache/zadd/{key}/{score}/{member}")
    Boolean zadd(@PathVariable(value = "key") String key,
                 @PathVariable(value = "score") Long score,
                 @PathVariable(value = "member") Long member);

    @GetMapping(value = "/cache/zrangebyscorecount/{key}/{start}/{end}")
    int zRangeByScoreCount(@PathVariable(value = "key") String key,
                           @PathVariable(value = "start") Double start,
                           @PathVariable(value = "end") Double end);

    @DeleteMapping(value = "/cache/zremove/{key}/{member}")
    void zRemove(@PathVariable(value = "key") String key, @PathVariable(value = "member") Long member);
}
