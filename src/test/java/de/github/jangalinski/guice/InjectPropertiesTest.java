package de.github.jangalinski.guice;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matcher;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

public class InjectPropertiesTest extends AbstractModule implements TypeListener, Matcher<Object> {

  private final Properties properties = new Properties();
  {
    properties.put("nameLengthMin", "5");
  }

  @Inject
  @Named
  private String nameLengthMin;

  @Test
  public void shouldInject5InFieldNameLengthMin() {
    Guice.createInjector(this).injectMembers(this);
    assertThat(nameLengthMin, is("5"));
  }

  @Override
  protected void configure() {
    bindListener(this, this);
    Names.bindProperties(binder(), properties);
  }

  public <I> void hear(final TypeLiteral<I> type, final TypeEncounter<I> encounter) {

  }

  public boolean matches(final Object t) {
    System.out.println(t);
    // TODO Auto-generated method stub
    return false;
  }

  public Matcher<Object> and(final Matcher<? super Object> other) {
    // TODO Auto-generated method stub
    return null;
  }

  public Matcher<Object> or(final Matcher<? super Object> other) {
    // TODO Auto-generated method stub
    return null;
  }

}
