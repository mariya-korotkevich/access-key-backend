package com.example.accesskeybackend.ipv6.service;

import java.net.UnknownHostException;

public interface Ipv6Service {
    boolean checkIpv6Support(String siteUrl) throws UnknownHostException;
}
