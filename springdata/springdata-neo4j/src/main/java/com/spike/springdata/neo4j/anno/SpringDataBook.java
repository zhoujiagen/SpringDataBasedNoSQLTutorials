package com.spike.springdata.neo4j.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于在源码中标注来源于Spring Data Book
 * @author zhoujiagen<br/>
 *         Aug 12, 2015 9:32:57 PM
 */
@Retention(RetentionPolicy.SOURCE)
@Target(value = { ElementType.TYPE, ElementType.LOCAL_VARIABLE })
public @interface SpringDataBook {
  String[] chapter() default { "" };
}
