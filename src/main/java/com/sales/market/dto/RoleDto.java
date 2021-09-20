package com.sales.market.dto;


import com.sales.market.model.Role;

public class RoleDto extends DtoBase<Role> {

    private String name;

    public RoleDto() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
