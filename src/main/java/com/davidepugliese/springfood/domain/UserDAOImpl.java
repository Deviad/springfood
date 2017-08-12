package com.davidepugliese.springfood.domain;

import com.davidepugliese.springfood.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private EntityManager em;

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

}
