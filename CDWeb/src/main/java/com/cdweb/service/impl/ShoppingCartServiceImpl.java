package com.cdweb.service.impl;

import com.cdweb.converter.ProductConverter;
import com.cdweb.converter.ShoppingCartConverter;
import com.cdweb.converter.UserConverter;
import com.cdweb.dto.ProductDTO;
import com.cdweb.dto.ShoppingCartDTO;
import com.cdweb.dto.UserDTO;
import com.cdweb.entity.ShoppingCartEntity;
import com.cdweb.repository.ShoppingCartRepository;
import com.cdweb.service.IProductService;
import com.cdweb.service.IShoppingCartService;
import com.cdweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements IShoppingCartService {
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private IProductService bookService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ShoppingCartConverter shoppingCartConverter;

    @Override
    public ShoppingCartDTO addProduct(long book_id, String email) {
        UserDTO user = this.userService.findByEmail(email);
        if (book_id != 0) {
            ProductDTO book = this.bookService.findById(book_id);
            ShoppingCartEntity entity = this.shoppingCartRepository.findCart(book.getId(), user.getId());
            ShoppingCartDTO cart = new ShoppingCartDTO();
            if (entity == null) {
                cart.setBook(book);
                cart.setUser(user);
                cart.setQuantity(1);
            } else {
                cart.setId(entity.getId());
                cart.setBook(book);
                cart.setUser(user);
                cart.setQuantity(entity.getQuantity() + 1);
            }
            ShoppingCartEntity test = this.shoppingCartConverter.toEntity(cart);
            ShoppingCartEntity entity1 = this.shoppingCartRepository.save(test);
            return this.shoppingCartConverter.toDTO(entity1);
        }
        return null;
    }

    @Override
    public List<ShoppingCartDTO> getProduct(String email) {
        UserDTO user = this.userService.findByEmail(email);
        List<ShoppingCartEntity> cartEntities = this.shoppingCartRepository.findAllByUser(user.getId());
        List<ShoppingCartDTO> cartList = new ArrayList<>();
        for (ShoppingCartEntity s : cartEntities) {
            cartList.add(this.shoppingCartConverter.toDTO(s));
        }
        return cartList;
    }

    @Override
    public List<ShoppingCartDTO> updateQuantity(long id, int quantity, String name) {
        UserDTO user = this.userService.findByEmail(name);
        ShoppingCartEntity cartEntity = this.shoppingCartRepository.findCart(id, user.getId());

        if (quantity == 0) {
            this.shoppingCartRepository.delete(cartEntity);
            return getProduct(user.getEmail());
        } else {
            cartEntity.setQuantity(quantity);
            this.shoppingCartRepository.save(cartEntity);
            return getProduct(user.getEmail());
        }
    }

    @Override
    public ShoppingCartDTO getId(long id) {
        return this.shoppingCartConverter.toDTO(this.shoppingCartRepository.getOne(id));
    }

    @Override
    public void delete(ShoppingCartDTO shoppingCartDTO) {
        this.shoppingCartRepository.delete(this.shoppingCartConverter.toEntity(shoppingCartDTO));
    }
}
