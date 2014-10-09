package com.bogie.common.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bogie.common.dao.GenericDao;

public class GenericDaoImpl<I extends Serializable, T> extends HibernateDaoSupport implements GenericDao<I, T>
{
    @SuppressWarnings("unchecked")
	public T get(Class<T> type, I id)
    {
        return (T)getHibernateTemplate().get(type, id);
    }

    public void saveOrUpdate(T value)
    {
        getHibernateTemplate().saveOrUpdate(value);
    }

    public void delete(T value)
    {
        getHibernateTemplate().delete(value);
    }
    
    @SuppressWarnings("unchecked")
	public List<T> find(String query)
    {
        return getHibernateTemplate().find(query);
    }
    
    @SuppressWarnings("unchecked")
	public List<T> find(String query, Object value)
    {
        return getHibernateTemplate().find(query, value);
    }
}
