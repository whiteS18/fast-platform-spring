package com.application.next.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSourceRouting extends AbstractRoutingDataSource {
   private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

   public static void setDataSourceKey(String key) {
       CONTEXT.set(key);
   }

   @Override
   protected Object determineCurrentLookupKey() {
       return CONTEXT.get();
   }

   public static void clear() {
       CONTEXT.remove();
   }
}