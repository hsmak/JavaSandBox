package com.connection.spring;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.ConcurrentTestRunner;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import java.sql.Connection;

@RunWith(ConcurrentTestRunner.class)
@ContextConfiguration(locations = {"classpath:datasource-context.xml"})
@Ignore
public class RunnableConnectionSpringTest {

    @Autowired
    ConnectionPoolSpring connectionPoolSpring;

    @Rule
    public ConcurrentRule rule = new ConcurrentRule();


    private TestContextManager testContextManager;
    @Before
    public void setUpStringContext() throws Exception {
        testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }

    @Test
    @Concurrent (count = 5)
    public void testMe() throws InterruptedException {
        Connection c = connectionPoolSpring.pullConnection();
        Thread.sleep(1000);
        connectionPoolSpring.pushConnection(c);
    }

}