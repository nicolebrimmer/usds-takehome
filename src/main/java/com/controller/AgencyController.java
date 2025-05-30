package com.controller;

import com.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/agencies")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @GetMapping("/getAllAgencies")
    public Map<String, String> getAllAgencies() {
        return agencyService.getAllAgencies();
    }

    @GetMapping("/getAgencyInfo/{agencySlug}")
    public Map<String, String> getAgencyInfo(@PathVariable String agencySlug) {
        return agencyService.getAgencyInfo(agencySlug);
    }
}
