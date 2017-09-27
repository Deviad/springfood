package com.davidepugliese.springfood.domain;

import com.davidepugliese.springfood.models.Post;
import com.davidepugliese.springfood.models.Role;
import com.davidepugliese.springfood.models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
@Repository
public class PostDAOImpl implements PostDAO {
    private final SessionFactory sessionFactory;

    private final EntityManager em;

    @Autowired
    public PostDAOImpl(SessionFactory sessionFactory, EntityManager em) {
        this.sessionFactory = sessionFactory;
        this.em = em;
    }

    @Override
    public void savePost(Post thePost) {

        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // save the customer ... finally LOL
        currentSession.save(thePost);
    }


    @Override
    public void deletePost(Integer thePostId) {
        Session currentSession = sessionFactory.getCurrentSession();
        String queryString = "DELETE Post p WHERE p.id = :thePostId";
        Query q = currentSession.createQuery(queryString)
                .setParameter("thePostId", thePostId);
        q.executeUpdate();
    }

    @Override
    public Post getPost(Integer thePostId) {

        // get current hibernate session

        String queryString = "FROM Post p WHERE  p.id = :thePostId";
        return (Post) em.createQuery(queryString).setParameter("thePostId", thePostId).getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> getPosts() {
        String queryString = "FROM Post p";
        return (List<Post>)  em.createQuery(queryString).getResultList();
    }


}
