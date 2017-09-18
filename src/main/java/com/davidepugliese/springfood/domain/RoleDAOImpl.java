package com.davidepugliese.springfood.domain;

import com.davidepugliese.springfood.models.Role;
import com.davidepugliese.springfood.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
@Repository
public class RoleDAOImpl implements RoleDAO {
    private final SessionFactory sessionFactory;
    private final EntityManager em;

    @Autowired
    public RoleDAOImpl(SessionFactory sessionFactory, EntityManager em) {
        this.sessionFactory = sessionFactory;
        this.em = em;
    }

    @Override
    public void saveRole(Role theRole) {

        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // save the customer ... finally LOL
        currentSession.save(theRole);
    }

    @Override
    @SuppressWarnings("unchecked")

    //TODO: this query needs to be remade properly

//    SELECT op.username, op.email, orders.p_id, orders.o_id, product.listed_price
//    FROM Orders order
//    INNER JOIN order.orderProcessing as op
//    INNER JOIN order.product as product
//    ORDER BY op.username


    public List<User> getUsersByRole(String rolename) {
        String queryString = "SELECT u.username FROM Role r JOIN  r.users u where  r.role = :rolename";
        List<User> foo = (List<User>) em.createQuery(queryString).setParameter("rolename", rolename).getResultList();
        System.out.println(foo);
        return foo;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getRoles() {
        String queryString = "FROM Role r";
        return (List<String>) em.createQuery(queryString).getResultList();
    }
}
