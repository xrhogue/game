/**
 * CommonTestCase.java
 */
package com.bogie.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * CommonTestCase is a base class that allows specific Test classes to derive from it an use its
 * generic task-based methods to run specific tests.
 * @author Richard Hogue
 * @version 1.0
 */
public class CommonTestCase
{
    protected Log                   log = LogFactory.getLog(getClass());
    protected ApplicationContext    applicationContext;
    
    /**
     * Default constructor
     */
    public CommonTestCase()
    {
        this(true);
    }
    
    /**
     * Default constructor
     * @param loadTestBeans true to load test beans, false otherwise
     */
    public CommonTestCase(boolean loadTestBeans)
    {
        if (loadTestBeans)
        {
            applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath*:/game-dao-beans.xml",
                                                                                 "classpath*:/game-bus-beans.xml"});
        }
    }
}

