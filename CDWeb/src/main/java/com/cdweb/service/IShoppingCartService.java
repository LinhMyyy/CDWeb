package com.cdweb.service;

import com.cdweb.dto.ShoppingCartDTO;

import java.util.List;

public interface IShoppingCartService {
    public ShoppingCartDTO addProduct(long book_id, String email);

    public List<ShoppingCartDTO> getProduct(String email);

    public List<ShoppingCartDTO> updateQuantity(long id, int quantity, String name);

    public ShoppingCartDTO getId(long id);

    public void delete(ShoppingCartDTO shoppingCartDTO);
}
