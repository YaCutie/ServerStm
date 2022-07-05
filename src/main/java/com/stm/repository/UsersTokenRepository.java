package com.stm.repository;

import com.stm.Entity.UsersToken;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersTokenRepository {
    private List<UsersToken> usersTokenList;

    public UsersTokenRepository(){
        usersTokenList = new ArrayList<>();
    }

    public List<UsersToken> getUsersTokenList() {
        return usersTokenList;
    }
}
