package com.hsbc.qe.ui.common;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config.properties"})
public interface Configuration extends Config {

    @Key("baseurl")
    String baseUrl();

    @Key("browsername")
    String browserName();

    @Key("isheadless")
    boolean isHeadLess();

    @Key("gridURL")
    String gridURL();

}
