package com.davidepugliese.springfood.domain;

import com.davidepugliese.springfood.models.Role;
import com.davidepugliese.springfood.models.User;
import com.davidepugliese.springfood.models.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserDAOImpl extends GenericDAO {
    private final SessionFactory sessionFactory;

    private final EntityManager em;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory, EntityManager em) {
        super(sessionFactory, em);
        this.sessionFactory = sessionFactory;
        this.em = em;
    }



    public void updateUser (User theUser) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.merge(theUser);
    }

    public void updateUserInfo (UserInfo userInfo) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.merge(userInfo);
    }

    @SuppressWarnings("unchecked")
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
