package de.ejb3buch.ticket2rock.cucumber.misc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.annotation.PostConstruct;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

/**
 * Simple GuiceModule that invokes methods annotated with {@link PostConstruct}.
 * 
 * @author carl.duevel@holisticon.de
 */

public class PostConstructModule implements Module {

	@Override
	public void configure(Binder binder) {
		binder.bindListener(Matchers.any(), new TypeListener() {
			@Override
			public <I> void hear(final TypeLiteral<I> typeLiteral,
					TypeEncounter<I> typeEncounter) {
				typeEncounter.register(new InjectionListener<I>() {
					@Override
					public void afterInjection(Object i) {
						for (Method method : i.getClass().getMethods()) {
							for (Annotation annotation : method
									.getAnnotations()) {
								if (annotation.annotationType()==PostConstruct.class) {
									try {
										method.invoke(i);
										break;
									} catch (Exception e) {
										throw new IllegalArgumentException(e);
									}
								}
							}
						}
					}
				});
			}
		});

	}
}
