package com.jun.java.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Book.class);
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor->{
            System.out.println(propertyDescriptor);
        });

    }
}
