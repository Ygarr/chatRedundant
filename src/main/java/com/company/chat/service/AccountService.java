package com.company.chat.service;

import com.company.chat.dao.HibernateDao;
import com.company.chat.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
@Component
public class AccountService {
    private final Map<String, UserProfile> SESSION_TO_PROFILE;

    @Autowired
    private HibernateDao dao;

    public AccountService() {
        SESSION_TO_PROFILE = new ConcurrentHashMap<>();
    }

    public void setDao(HibernateDao dao) {
        this.dao = dao;
    }

    public void addAccount(UserProfile user) {
        dao.saveUserProfile(user);
    }

    public UserProfile getAccountByLogin(String login) {
        return dao.getUserProfileByNickname(login);
    }

    public void addSessionForUser(UserProfile user, String sessionId) {
        SESSION_TO_PROFILE.put(sessionId, user);
    }

    public UserProfile getAccountBySession(String sessionId) {
        return SESSION_TO_PROFILE.get(sessionId);
    }

    public void deleteSession(String sessionId) {
        SESSION_TO_PROFILE.remove(sessionId);
    }
}
