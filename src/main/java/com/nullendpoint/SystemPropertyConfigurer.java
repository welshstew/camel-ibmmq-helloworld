package com.nullendpoint;

/**
 * Created by swinchester on 2/03/2017.
 */
public class SystemPropertyConfigurer {

    public SystemPropertyConfigurer(String trustStoreLocation, String trustStorePassword, String javaSecurityPath){
        System.setProperty("javax.net.ssl.trustStore", trustStoreLocation);
        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
        System.setProperty("java.security.properties", javaSecurityPath);
        System.setProperty("security.overridePropertiesFile", "true");
    }
}
