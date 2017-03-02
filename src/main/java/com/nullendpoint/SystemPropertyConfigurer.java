package com.nullendpoint;

/**
 * Created by swinchester on 2/03/2017.
 */
public class SystemPropertyConfigurer {

    public SystemPropertyConfigurer(String trustStoreLocation, String trustStorePassword){
        System.setProperty("javax.net.ssl.trustStore", trustStoreLocation);
        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
    }
}
