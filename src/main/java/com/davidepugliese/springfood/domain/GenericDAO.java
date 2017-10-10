package com.davidepugliese.springfood.domain;

import com.davidepugliese.springfood.models.GenericEntity;
import com.davidepugliese.springfood.models.Post;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.net.www.content.text.Generic;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;


@Transactional
@Repository
public class  GenericDAO<T extends GenericEntity> {
    private final SessionFactory sessionFactory;

    private final EntityManager em;
    private final Class<T> genericType;

    @Autowired
    @SuppressWarnings("unchecked")
    public GenericDAO (SessionFactory sessionFactory, EntityManager em) {
        this.sessionFactory = sessionFactory;
        this.em = em;
        this.genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(),GenericDAO.class);
    }


    public void save(T entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(entity);
    }


    @SuppressWarnings("unchecked")
    public T get(Integer entityId) {

        // get current hibernate session

        String queryString = String.format("FROM %s p WHERE  p.id = :entityId", this.genericType.getName());
        return (T) em.createQuery(queryString).setParameter("thePostId", entityId).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<T> getList() {
        Session currentSession = sessionFactory.getCurrentSession();
        // get current hibernate session
        Criteria cr = currentSession.createCriteria(this.genericType.getName());
        return (List<T>) cr.list();
    }
    @SuppressWarnings("unchecked")
    public void delete(Integer id) {

    }
}
