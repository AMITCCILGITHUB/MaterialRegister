package org.map.service;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.map.hibernate.dao.UserData;
import org.map.login.Login;

public class UserValidation extends Service<Boolean> {

    @Override
    protected Task<Boolean> createTask() {
        return new Task<Boolean>() {

            @Override
            protected Boolean call() {
                return Boolean.valueOf(UserData.validateUser(Login.getLoginPanel().getUserMaster()));
            }
        };
    }
}