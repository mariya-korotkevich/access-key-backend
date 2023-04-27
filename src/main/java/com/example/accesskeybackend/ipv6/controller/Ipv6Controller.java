package com.example.accesskeybackend.ipv6.controller;

import com.example.accesskeybackend.exception.IllegalArgumentException;
import com.example.accesskeybackend.ipv6.service.Ipv6Service;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

@RestController
@RequestMapping("/api/web/checkIpv6Support")
public class Ipv6Controller {
    private final Ipv6Service service;

    public Ipv6Controller(Ipv6Service service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Boolean> checkIpv6Support(
            @Parameter(description = "site url to check for IPv6 support")
            @RequestParam("siteUrl") String siteUrl){
        try {
            return ResponseEntity.ok(service.checkIpv6Support(siteUrl));
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }
}