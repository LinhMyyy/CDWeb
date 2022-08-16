package com.cdweb.controller.web;

import com.cdweb.api.web.output.CartOutput;
import com.cdweb.dto.ShoppingCartDTO;
import com.cdweb.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@RestController
public class ShoppingCartController {
    @Autowired
    private IShoppingCartService shoppingCartService;

    @GetMapping("/gio-hang")
    public ModelAndView shoppingCart(Principal principal) {
        return principal == null ? new ModelAndView("web/dang-nhap.html") : new ModelAndView("web/gio-hang.html");
    }

    @GetMapping("/them-san-pham")
    public ShoppingCartDTO addProduct(@RequestParam(name = "book_id", required = false, defaultValue = "0") long book_id, Principal principal) {
        if (principal == null) {
            return null;
        }
        String email = principal.getName();
        return this.shoppingCartService.addProduct(book_id, email);
    }


    @GetMapping("/cart-item")
    public CartOutput getItems(Principal principal) {
        CartOutput output = new CartOutput();
        String email = principal.getName();
        List<ShoppingCartDTO> cartList = this.shoppingCartService.getProduct(email);
        double total = 0;
        for (ShoppingCartDTO cart : cartList) {
            total += cart.getBook().getPrice() * (1 - cart.getBook().getDiscount() / 100) * cart.getQuantity();
        }
        output.setTotal(total);
        output.setList(cartList);
        return output;
    }

    @GetMapping("/update-quantity")
    public CartOutput updateQuantity(@Param("id") long id, @Param("quantity") int quantity, Principal principal) {

        CartOutput output = new CartOutput();
        String email = principal.getName();
        List<ShoppingCartDTO> cartList = this.shoppingCartService.updateQuantity(id, quantity, email);
        double total = 0;
        for (ShoppingCartDTO cart : cartList) {
            total += cart.getBook().getPrice() * (1 - cart.getBook().getDiscount() / 100) * cart.getQuantity();
        }
        output.setTotal(total);
        output.setList(cartList);
        return output;

    }
}
