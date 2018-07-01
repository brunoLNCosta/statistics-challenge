package com.n26.challenge.infrastructure.rest.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Just a sugar sintax for @RestController and @RequestMapping
 * 
 * @author brunocosta
 *
 */

@Retention(RUNTIME)
@Target(TYPE)
@RestController
@RequestMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public @interface RestResource {
	
	@AliasFor(annotation = RequestMapping.class, attribute = "path")
	String[] path() default {};
	
	@AliasFor(annotation = RequestMapping.class, attribute = "produces")
	String[] produces() default {};
	
	@AliasFor(annotation = RequestMapping.class, attribute = "consumes")
	String[] consumes() default {};
	
}
