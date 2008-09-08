package org.jboss.webbeans.introspector;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Base class for implementing AnnotatedItem. This implementation assumes 
 * the annotationMap is immutable.
 * 
 * @author pmuir
 *
 */
public class SimpleAnnotatedType<T> extends AbstractAnnotatedItem<Class<? extends T>> implements AnnotatedType<T>
{
   
   private Class<? extends T> annotatedClass;
   
   public SimpleAnnotatedType(Class<? extends T> annotatedClass, Map<Class<? extends Annotation>, Annotation> annotationMap)
   {
      super(annotationMap);
      this.annotatedClass = annotatedClass;
   }
   
   public SimpleAnnotatedType(Class<? extends T> annotatedClass)
   {
      this(annotatedClass, buildAnnotationMap(annotatedClass));
   }
   
   public Class<? extends T> getAnnotatedClass()
   {
      return annotatedClass;
   }
   
   @Override
   public String toString()
   {
      return annotatedClass + " " + super.getAnnotationMap().toString();
   }
   
   public Class<? extends T> getDelegate()
   {
      return annotatedClass;
   }

}