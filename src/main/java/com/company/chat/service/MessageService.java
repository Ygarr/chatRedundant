package com.company.chat.service;

import com.company.chat.dao.HibernateDao;
import com.company.chat.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class MessageService {

    @Autowired
    private HibernateDao dao;

    public void setDao(HibernateDao dao) {
        this.dao = dao;
    }

    public void saveMessage(Message message) {
        dao.saveMessage(message);
    }
}
