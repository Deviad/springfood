package com.davidepugliese.springfood.domain;

import com.davidepugliese.springfood.models.Role;
import com.davidepugliese.springfood.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Set;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {
    private final SessionFactory sessionFactory;

    private final EntityManager em;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory, EntityManager em) {
        this.sessionFactory = sessionFactory;
        this.em = em;
    }

    @Override
    public void saveUser(User theUser) {

        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // save the customer ... finally LOL
        currentSession.save(theUser);
    }

    @Override
    public User getUser(Integer theUserId) {

        // get current hibernate session

        String queryString = "FROM User u WHERE  u.id = :theUserId";
        return (User) em.createQuery(queryString).setParameter("theUserId", theUserId).getSingleResult();
    }

    @Override
    public User getUserByUsername(String username)  {

        // get current hibernate session

        String queryString = "FROM User u WHERE  u.username = :username";
        return (User) em.createQuery(queryString).setParameter("username", username).getSingleResult();
    }
//    SELECT op.username, op.email, orders.p_id, orders.o_id, product.listed_price
//    FROM Orders order
//    INNER JOIN order.orderProcessing as op
//    INNER JOIN order.product as product
//    ORDER BY op.username

//    @Override
//    @SuppressWarnings("unchecked")
//    public Set<Role> getRolesByUsername(String username) {
//        String queryString = "FROM User u WHERE  u.username = :username";
//        User user = (User) em.createQuery(queryString).setParameter("username", username).getSingleResult();
//        return (Set<Role>) user.getRoles();
//    }

}
