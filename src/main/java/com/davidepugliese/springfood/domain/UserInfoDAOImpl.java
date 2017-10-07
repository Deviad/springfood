package com.davidepugliese.springfood.domain;
import com.davidepugliese.springfood.models.User;
import com.davidepugliese.springfood.models.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
@Repository
public class UserInfoDAOImpl implements UserInfoDAO {

    private final SessionFactory sessionFactory;
    private final EntityManager em;


    public UserInfoDAOImpl (SessionFactory sessionFactory, EntityManager em) {
        this.sessionFactory = sessionFactory;
        this.em = em;
    }


    @Override
    public void saveUserInfo(UserInfo userInfo) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // save the customer ... finally LOL
        currentSession.save(userInfo);
    }

    //        String queryString = "SELECT u.username FROM Role r JOIN  r.users u where  r.role = :rolename";


    @Override
    public UserInfo getInfoByUserId(Integer theUserId) {

        String queryString = "FROM UserInfo ui WHERE  ui.id = :theUserId";
        return (UserInfo) em.createQuery(queryString).setParameter("theUserId", theUserId).getSingleResult();

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsersByLastName(String lastName) {
        String queryString = "SELECT u FROM UserInfo ui JOIN  ui.id u where  ui.lastName = :lastName";
        List<User> foo = (List<User>) em.createQuery(queryString).setParameter("lastName", lastName).getResultList();
        System.out.println(foo);
        return foo;
    }
}
