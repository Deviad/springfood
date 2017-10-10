package com.davidepugliese.springfood.domain;

import com.davidepugliese.springfood.models.Post;
import com.davidepugliese.springfood.models.Role;
import com.davidepugliese.springfood.models.User;
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
import java.util.List;

@Transactional
@Repository
@Qualifier("roleDao")
public class RoleDAOImpl extends GenericDAO {
    private final SessionFactory sessionFactory;
    private final EntityManager em;

    @Autowired
    public RoleDAOImpl(SessionFactory sessionFactory, EntityManager em) {
        super(sessionFactory, em);
        this.sessionFactory = sessionFactory;
        this.em = em;
    }


    @SuppressWarnings("unchecked")

//    SELECT op.username, op.email, orders.p_id, orders.o_id, product.listed_price
//    FROM Orders order
//    INNER JOIN order.orderProcessing as op
//    INNER JOIN order.product as product
//    ORDER BY op.username
    public List<User> getUsersByRole(String rolename) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery q = cb.createQuery(Role.class);
        Root o = q.from(Role.class);
        o.fetch("users", JoinType.INNER);
        q.select(o);
        q.where(cb.equal(o.get("role"), rolename));
        return (List<User>)this.em.createQuery(q).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> getList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery q = cb.createQuery(Role.class);
        Root o = q.from(Role.class);
        o.fetch("users", JoinType.INNER);
        q.select(o);
        return (List<Role>)this.em.createQuery(q).getSingleResult();
    }
}
