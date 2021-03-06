package com.squareup.otto;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class AnnotatedHandlerFinder
{
  private static final ConcurrentMap<Class<?>, Map<Class<?>, Method>> PRODUCERS_CACHE = new ConcurrentHashMap();
  private static final ConcurrentMap<Class<?>, Map<Class<?>, Set<Method>>> SUBSCRIBERS_CACHE = new ConcurrentHashMap();
  
  static Map<Class<?>, EventProducer> findAllProducers(Object paramObject)
  {
    Class localClass = paramObject.getClass();
    HashMap localHashMap = new HashMap();
    Object localObject = (Map)PRODUCERS_CACHE.get(localClass);
    if (localObject == null)
    {
      localObject = new HashMap();
      loadAnnotatedProducerMethods(localClass, (Map)localObject);
    }
    if (!((Map)localObject).isEmpty())
    {
      Iterator localIterator = ((Map)localObject).entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        EventProducer localEventProducer = new EventProducer(paramObject, (Method)localEntry.getValue());
        localHashMap.put(localEntry.getKey(), localEventProducer);
      }
    }
    return localHashMap;
  }
  
  static Map<Class<?>, Set<EventHandler>> findAllSubscribers(Object paramObject)
  {
    Class localClass = paramObject.getClass();
    HashMap localHashMap = new HashMap();
    Object localObject = (Map)SUBSCRIBERS_CACHE.get(localClass);
    if (localObject == null)
    {
      localObject = new HashMap();
      loadAnnotatedSubscriberMethods(localClass, (Map)localObject);
    }
    if (!((Map)localObject).isEmpty())
    {
      Iterator localIterator1 = ((Map)localObject).entrySet().iterator();
      while (localIterator1.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator1.next();
        HashSet localHashSet = new HashSet();
        Iterator localIterator2 = ((Set)localEntry.getValue()).iterator();
        while (localIterator2.hasNext()) {
          localHashSet.add(new EventHandler(paramObject, (Method)localIterator2.next()));
        }
        localHashMap.put(localEntry.getKey(), localHashSet);
      }
    }
    return localHashMap;
  }
  
  private static void loadAnnotatedMethods(Class<?> paramClass, Map<Class<?>, Method> paramMap, Map<Class<?>, Set<Method>> paramMap1)
  {
    Method[] arrayOfMethod = paramClass.getDeclaredMethods();
    int i = arrayOfMethod.length;
    int j = 0;
    if (j < i)
    {
      Method localMethod = arrayOfMethod[j];
      if (localMethod.isBridge()) {}
      for (;;)
      {
        j++;
        break;
        if (localMethod.isAnnotationPresent(Subscribe.class))
        {
          Class[] arrayOfClass2 = localMethod.getParameterTypes();
          if (arrayOfClass2.length != 1) {
            throw new IllegalArgumentException("Method " + localMethod + " has @Subscribe annotation but requires " + arrayOfClass2.length + " arguments.  Methods must require a single argument.");
          }
          Class localClass2 = arrayOfClass2[0];
          if (localClass2.isInterface()) {
            throw new IllegalArgumentException("Method " + localMethod + " has @Subscribe annotation on " + localClass2 + " which is an interface.  Subscription must be on a concrete class type.");
          }
          if ((0x1 & localMethod.getModifiers()) == 0) {
            throw new IllegalArgumentException("Method " + localMethod + " has @Subscribe annotation on " + localClass2 + " but is not 'public'.");
          }
          Object localObject = (Set)paramMap1.get(localClass2);
          if (localObject == null)
          {
            localObject = new HashSet();
            paramMap1.put(localClass2, localObject);
          }
          ((Set)localObject).add(localMethod);
        }
        else if (localMethod.isAnnotationPresent(Produce.class))
        {
          Class[] arrayOfClass1 = localMethod.getParameterTypes();
          if (arrayOfClass1.length != 0) {
            throw new IllegalArgumentException("Method " + localMethod + "has @Produce annotation but requires " + arrayOfClass1.length + " arguments.  Methods must require zero arguments.");
          }
          if (localMethod.getReturnType() == Void.class) {
            throw new IllegalArgumentException("Method " + localMethod + " has a return type of void.  Must declare a non-void type.");
          }
          Class localClass1 = localMethod.getReturnType();
          if (localClass1.isInterface()) {
            throw new IllegalArgumentException("Method " + localMethod + " has @Produce annotation on " + localClass1 + " which is an interface.  Producers must return a concrete class type.");
          }
          if (localClass1.equals(Void.TYPE)) {
            throw new IllegalArgumentException("Method " + localMethod + " has @Produce annotation but has no return type.");
          }
          if ((0x1 & localMethod.getModifiers()) == 0) {
            throw new IllegalArgumentException("Method " + localMethod + " has @Produce annotation on " + localClass1 + " but is not 'public'.");
          }
          if (paramMap.containsKey(localClass1)) {
            throw new IllegalArgumentException("Producer for type " + localClass1 + " has already been registered.");
          }
          paramMap.put(localClass1, localMethod);
        }
      }
    }
    PRODUCERS_CACHE.put(paramClass, paramMap);
    SUBSCRIBERS_CACHE.put(paramClass, paramMap1);
  }
  
  private static void loadAnnotatedProducerMethods(Class<?> paramClass, Map<Class<?>, Method> paramMap)
  {
    loadAnnotatedMethods(paramClass, paramMap, new HashMap());
  }
  
  private static void loadAnnotatedSubscriberMethods(Class<?> paramClass, Map<Class<?>, Set<Method>> paramMap)
  {
    loadAnnotatedMethods(paramClass, new HashMap(), paramMap);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/squareup/otto/AnnotatedHandlerFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */