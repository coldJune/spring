package com.jun.bean.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;
import reactor.util.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal级别Scope
 * @Author dengxiaojun
 * @Date 2020/11/19 21:43
 * @Version 1.0
 */
public class ThreadLocalScope implements Scope {
    public static final String SCOPE_NAME="thread-local";

    private NamedThreadLocal<Map<String,Object>> threadLocal = new NamedThreadLocal("thread-local-scope"){
        @Override
        protected Object initialValue() {
            return new HashMap<String,Object>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String,Object> context = getContext();
        Object object = context.get(name);
        if(object == null){
            object = objectFactory.getObject();
            context.putIfAbsent(name,object);
        }
        return object;
    }

    @NonNull
    private Map<String,Object> getContext(){
        return this.threadLocal.get();
    }

    @Override
    public Object remove(String name) {
        Map<String,Object> context = getContext();
        return  context.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return getContext().get(key);
    }

    @Override
    public String getConversationId() {
        long threadId = Thread.currentThread().getId();
        return String.valueOf(threadId);
    }
}
