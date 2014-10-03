package com.github.jangalinski;



import org.assertj.core.api.Assertions;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class _26177485Test {

    //@Singleton
    public static class A {
        private int number = new Random().nextInt();
    }

    public static class B {

        @Inject
        private Instance<A> aInstance;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Weld weld = new Weld();

    private B b;

    @Test
    public void _() {
        assertThat(b.aInstance).isNotNull();

        for (int i = 0; i<10; i++) {
            logger.info("{}", b.aInstance.get().number);
        }
    }


    @Before
    public void setUp() {
        final WeldContainer container = weld.initialize();

        b = container.instance().select(B.class).get();
    }

    @After
    public void tearDown() {
        weld.shutdown();
    }
}
