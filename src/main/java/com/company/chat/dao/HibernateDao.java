package com.company.chat.dao;

import com.company.chat.model.Message;
import com.company.chat.model.UserProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Repository
public class HibernateDao {

    @Autowired
    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void saveUserProfile(UserProfile user) {
        Session session = sessionFactory.openSession();
        session.save(user);
    }

    @Transactional
    public UserProfile getUserProfileByNickname(String nickname) {
        Session session = sessionFactory.openSession();
        Query<UserProfile> query = session.createQuery("FROM UserProfile U WHERE U.nickname = :nickname", UserProfile.class);
        query.setParameter("nickname", nickname);
        return query.uniqueResult();
    }

    @Transactional
    public void saveMessage(Message message) {
        Session session = sessionFactory.openSession();
        session.save(message);
    }
}
