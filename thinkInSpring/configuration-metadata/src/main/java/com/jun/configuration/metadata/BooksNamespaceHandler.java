package com.jun.configuration.metadata;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.*;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * "books.xsd" {@link NamespaceHandler} 实现
 * @author: dengxiaojun-001
 * @date 2021/3/1 15:03
 * Description:
 */
public class  BooksNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        // 将”book“ 元素注册对应的 BeanDefinitionParser实现
        registerBeanDefinitionParser("book", new BookBeanDefinitionParser());
    }

    /**
     * ”book“ 元素的 {@link BeanDefinitionParser} 实现
     */
    private static class BookBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

        @Override
        protected Class<?> getBeanClass(Element element) {
            return Book.class;
        }

        @Override
        protected void doParse(Element element, BeanDefinitionBuilder builder) {
            setPropertyValue("price",element,builder);
            setPropertyValue("name",element,builder);
            setPropertyValue("city",element,builder);
        }

        private void setPropertyValue(String attributeName, Element element, BeanDefinitionBuilder builder){
            String attributeValue = element.getAttribute(attributeName);
            if(StringUtils.hasText(attributeValue)){
                builder.addPropertyValue(attributeName,attributeValue); // -> <property name="" value="">
            }
        }
    }
}
