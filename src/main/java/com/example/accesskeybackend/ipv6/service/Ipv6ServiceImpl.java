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
        if (siteUrl == null){
            throw new UnknownHostException();
        }
        return Arrays.stream(InetAddress.getAllByName(getHostName(siteUrl)))
                .anyMatch(inetAddress -> inetAddress instanceof Inet6Address);
    }

    private String getHostName(String siteUrl){
        int indexFrom = siteUrl.indexOf("://");
        if (indexFrom != -1){
            siteUrl = siteUrl.substring(indexFrom + 3);
        }
        int indexTo = siteUrl.indexOf("/");
        if (indexTo != -1){
            siteUrl = siteUrl.substring(0, indexTo);
        }
        return siteUrl;
    }
}