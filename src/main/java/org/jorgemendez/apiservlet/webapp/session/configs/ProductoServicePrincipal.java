package org.jorgemendez.apiservlet.webapp.session.configs;

import jakarta.inject.Qualifier;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD,FIELD,PACKAGE,TYPE,CONSTRUCTOR,PARAMETER})
public @interface ProductoServicePrincipal {
}
