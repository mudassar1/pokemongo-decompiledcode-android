package com.fasterxml.jackson.databind.module;

import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class SimpleAbstractTypeResolver
  extends AbstractTypeResolver
  implements Serializable
{
  private static final long serialVersionUID = 8635483102371490919L;
  protected final HashMap<ClassKey, Class<?>> _mappings = new HashMap();
  
  public <T> SimpleAbstractTypeResolver addMapping(Class<T> paramClass, Class<? extends T> paramClass1)
  {
    if (paramClass == paramClass1) {
      throw new IllegalArgumentException("Can not add mapping from class to itself");
    }
    if (!paramClass.isAssignableFrom(paramClass1)) {
      throw new IllegalArgumentException("Can not add mapping from class " + paramClass.getName() + " to " + paramClass1.getName() + ", as latter is not a subtype of former");
    }
    if (!Modifier.isAbstract(paramClass.getModifiers())) {
      throw new IllegalArgumentException("Can not add mapping from class " + paramClass.getName() + " since it is not abstract");
    }
    this._mappings.put(new ClassKey(paramClass), paramClass1);
    return this;
  }
  
  public JavaType findTypeMapping(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
  {
    Class localClass1 = paramJavaType.getRawClass();
    Class localClass2 = (Class)this._mappings.get(new ClassKey(localClass1));
    if (localClass2 == null) {}
    for (JavaType localJavaType = null;; localJavaType = paramDeserializationConfig.getTypeFactory().constructSpecializedType(paramJavaType, localClass2)) {
      return localJavaType;
    }
  }
  
  public JavaType resolveAbstractType(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
  {
    return null;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/module/SimpleAbstractTypeResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */