package com.davidepugliese.springfood.domain;

import com.davidepugliese.springfood.models.Post;
import com.davidepugliese.springfood.models.Role;
import com.davidepugliese.springfood.models.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
@Repository
@Qualifier("postDao")
public class PostDAOImpl extends GenericDAO {
    private final SessionFactory sessionFactory;

    private final EntityManager em;

    @Autowired
    public PostDAOImpl(SessionFactory sessionFactory, EntityManager em) {
        super(sessionFactory, em);
        this.sessionFactory = sessionFactory;
        this.em = em;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Post> getList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery q = cb.createQuery(Post.class);
        Root o = q.from(Post.class);
        o.fetch("users", JoinType.INNER);
        q.select(o);
        return (List<Post>)this.em.createQuery(q).getResultList();
    }

}
