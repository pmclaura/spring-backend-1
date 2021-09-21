package com.sales.market.service;

import com.sales.market.model.Sale;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.SaleRepository;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl extends GenericServiceImpl<Sale> implements SaleService{
    private SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    protected GenericRepository<Sale> getRepository() {
        return saleRepository;
    }
}
