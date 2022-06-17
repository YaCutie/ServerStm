package com.stm.dto;

import com.stm.Entity.Client;

public class GetClientByIdRsDto {
    private Client client;

    public Client getUser() {
        return client;
    }

    public void setUser(Client user) {
        this.client = client;
    }
}
