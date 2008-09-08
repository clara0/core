package org.jboss.webbeans.introspector;


/**
 * AnnotatedType provides a uniform access to the annotations on an annotated
 * class defined either in Java or XML 
 * 
 * @author Pete Muir
 *
 */
public interface AnnotatedType<T> extends AnnotatedItem<Class<? extends T>>
{
   
   /**
    * Return the class of the annotated item. If this annotatedItem isn't in use
    * then this method should return null
    */
   public Class<? extends T> getAnnotatedClass();
   
}