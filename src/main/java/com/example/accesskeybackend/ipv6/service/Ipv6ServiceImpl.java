package com.example.accesskeybackend.ipv6.service;

import org.springframework.stereotype.Component;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

@Component
public class Ipv6ServiceImpl implements Ipv6Service{
    @Override
    public boolean checkIpv6Support(String siteUrl) throws UnknownHostException {
        return Arrays.stream(InetAddress.getAllByName(getHostName(siteUrl)))
                .anyMatch(inetAddress -> inetAddress instanceof Inet6Address);
    }

    private String getHostName(String siteUrl){
        siteUrl = siteUrl.replaceAll("http://", "");
        siteUrl = siteUrl.replaceAll("https://", "");
        int index = siteUrl.indexOf("/");
        if (index != -1){
            siteUrl = siteUrl.substring(0, index);
        }
        return siteUrl;
    }
}