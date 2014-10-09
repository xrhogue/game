/**
 * ServletWrappingController.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.server.spring;

import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * ServletWrappingController
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class ServletWrappingController extends AbstractController implements BeanNameAware, InitializingBean, DisposableBean
{
    private Class<?>      servletClass;
    private String     servletName;
    private Properties initParameters = new Properties();
    private String     beanName;
    private Servlet    servletInstance;

    public void setServletClass(Class<?> servletClass)
    {
        this.servletClass = servletClass;
    }

    public void setServletName(String servletName)
    {
        this.servletName = servletName;
    }

    public void setInitParameters(Properties initParameters)
    {
        this.initParameters = initParameters;
    }

    public void setBeanName(String name)
    {
        this.beanName = name;
    }

    public void setServletInstance(Servlet servletInstance)
    {
        this.servletInstance = servletInstance;
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        if (this.servletInstance == null)
        {
            throw new IllegalArgumentException("servletInstance is required");
        }
        
        if (!Servlet.class.isAssignableFrom(servletInstance.getClass()))
        {
            throw new IllegalArgumentException("servletInstance [" + this.servletClass.getName() + "] needs to implement interface [javax.servlet.Servlet]");
        }
        
        if (this.servletName == null)
        {
            this.servletName = this.beanName;
        }

        this.servletInstance.init(new DelegatingServletConfig());
    }

    /**
     * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        this.servletInstance.service(request, response);
        return null;
    }

    /**
     * @see org.springframework.beans.factory.DisposableBean#destroy()
     */
    public void destroy()
    {
        this.servletInstance.destroy();
    }

    /**
     * DelegatingServletConfig 
     * 
     * @author Richard Hogue
     * @version 1.0
     */
    private class DelegatingServletConfig implements ServletConfig
    {
        /**
         * @see javax.servlet.ServletConfig#getServletName()
         */
        public String getServletName()
        {
            return servletName;
        }

        /**
         * @see javax.servlet.ServletConfig#getServletContext()
         */
        public ServletContext getServletContext()
        {
            return getWebApplicationContext().getServletContext();
        }

        /**
         * @see javax.servlet.ServletConfig#getInitParameter(java.lang.String)
         */
        public String getInitParameter(String paramName)
        {
            return initParameters.getProperty(paramName);
        }

        /**
         * @see javax.servlet.ServletConfig#getInitParameterNames()
         */
        public Enumeration<?> getInitParameterNames()
        {
            return initParameters.keys();
        }
    }
}