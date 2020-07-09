package com.jun.java.beans;

import java.beans.*;
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Book.class);
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor->{
            System.out.println(propertyDescriptor);
            //propertyDescriptor 允许添加属性编辑器 PropertyEditor
            //GUI --> text(String) ->PropertyTpe
            Class<?> propertyType = propertyDescriptor.getPropertyType();
            String propertyName = propertyDescriptor.getName();
            if("price".equals(propertyName)){//为price这个属性增加propertyEidtor
                //String -> Integer
                propertyDescriptor.setPropertyEditorClass(String2IntegerPropertyEditor.class);
//                propertyDescriptor.createPropertyEditor();
            }
            System.out.println(propertyDescriptor);
        });

    }

    static class String2IntegerPropertyEditor extends PropertyEditorSupport{
        @Override
        public void setAsText(String text) throws IllegalArgumentException {

            setValue(Integer.valueOf(text)+3);
        }
    }
}
