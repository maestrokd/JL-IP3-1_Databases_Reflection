package com.infoPulse.lessons.DaoTools;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DaoField {

    String name();
    boolean id() default false;
}
