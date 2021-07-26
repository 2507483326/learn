package com.epat.dutyChain;

/**
 * @author 李涛
 * @date : 2021/7/22 13:19
 */
public class RoleCheckMiddleware  extends Middleware{

    @Override
    public boolean check(String email, String password) {
        if (email.equals("admin@example.com")) {
            System.out.println("Hello, admin!");
            return true;
        }
        System.out.println("Hello, user!");
        return checkNext(email, password);
    }

}
