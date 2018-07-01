package com.n26.challenge.infrastructure.rest.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
@ResponseStatus
@RequestMapping
public @interface RestService {
	
	// TODO Corrigir Swagger springfox para que passe a exibir corretamente os status de retorno.
	@AliasFor(annotation = ResponseStatus.class, attribute = "code")
	HttpStatus code();
	
	@AliasFor(annotation = RequestMapping.class, attribute = "method")
	RequestMethod[] method() default {};
	
	@AliasFor(annotation = RequestMapping.class, attribute = "path")
	String[] path() default {};
}
