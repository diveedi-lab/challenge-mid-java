package com.diveedi.rest;

import com.diveedi.rest.resource.PersonsResource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>(super.getClasses());

        classes.add(PersonsResource.class);

        return classes;
    }
}