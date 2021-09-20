package com.sales.market.service;

import com.sales.market.model.Role;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role> implements RoleService {

    private RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<Role> getRepository() {
        return repository;
    }

}
