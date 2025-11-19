package com.mashibing.api.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * beacon-cache模块client
 */
@FeignClient(value = "beacon-cache")
public class BeaconCacheClient {
}
