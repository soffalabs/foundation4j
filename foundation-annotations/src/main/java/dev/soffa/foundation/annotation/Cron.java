package dev.soffa.foundation.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Cron {

    String EVERY_5_SECONDS = "*/5 * * * * *";
    String EVERY_10_SECONDS = "*/10 * * * * *";
    String EVERY_30_SECONDS = "*/30 * * * * *";

    String EVERY_1_MINUTE = "* * * * *";
    String EVERY_5_MINUTES = "*/5 * * * *";
    String EVERY_10_MINUTES = "*/10 * * * *";
    String EVERY_15_MINUTES = "*/15 * * * *";
    String EVERY_30_MINUTES = "*/30 * * * *";

    String HOURLY = "0 * * * *";

    String value();
}
