package com.company.chat.service;

import org.apache.commons.codec.digest.DigestUtils;
import com.company.chat.model.UserProfile;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class PasswordEncryptor {

    public void encryptPassword(UserProfile user) {
        String sha1Hex = DigestUtils.sha1Hex(user.getPassword() +
                DigestUtils.sha1Hex(user.getNickname()));
        user.setPassword(sha1Hex);
    }

}
