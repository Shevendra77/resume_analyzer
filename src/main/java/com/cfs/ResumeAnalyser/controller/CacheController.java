package com.cfs.ResumeAnalyser.controller;

import com.cfs.ResumeAnalyser.service.DashboardService;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    private final DashboardService dashboardService;
    private final CacheManager cacheManager;

    public CacheController(DashboardService dashboardService, CacheManager cacheManager) {
        this.dashboardService = dashboardService;
        this.cacheManager = cacheManager;
    }

    // Specific user ka cache clear
    @DeleteMapping("/dashboard/{email}")
    public ResponseEntity<String> clearDashboardCache(@PathVariable String email) {
        dashboardService.evictDashboardCache(email);
        return ResponseEntity.ok("Cache cleared for: " + email);
    }

    // Saara cache clear
    @DeleteMapping("/all")
    public ResponseEntity<String> clearAllCache() {
        cacheManager.getCacheNames().forEach(cacheName -> {
            var cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.clear();
            }
        });
        return ResponseEntity.ok("All cache cleared!");
    }
}