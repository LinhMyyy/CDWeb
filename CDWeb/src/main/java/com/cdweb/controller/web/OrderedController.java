package com.cdweb.controller.web;

import com.cdweb.api.web.input.OrderedInput;
import com.cdweb.api.web.output.CartOutput;
import com.cdweb.dto.*;
import com.cdweb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class OrderedController {
    @Autowired
    private IShoppingCartService shoppingCartService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IOrderedService orderedService;
    @Autowired
    private IProductService bookService;
    @Autowired
    private IOrderItemService orderItemService;

    @PostMapping("/dat-hang")
    public ModelAndView payment(@ModelAttribute("orderedInput") OrderedInput orderedInput, Principal principal) {
        List<OrderedItemDTO> itemList = new ArrayList<>();
        double total_price = 0;
        for (Long s : orderedInput.getCarts()) {
            ShoppingCartDTO shoppingCartDTO = this.shoppingCartService.getId(s);
            double total = shoppingCartDTO.getBook().getPrice() * (1 - shoppingCartDTO.getBook().getDiscount() / 100) * shoppingCartDTO.getQuantity();
            total_price += total;
            OrderedItemDTO orderedItem = new OrderedItemDTO();
            orderedItem.setTotalPrice(total);
            if (shoppingCartDTO.getQuantity() > shoppingCartDTO.getBook().getQuantity()) {
                orderedItem.setQuantity(shoppingCartDTO.getBook().getQuantity());
            } else {
                orderedItem.setQuantity(shoppingCartDTO.getQuantity());
            }

            orderedItem.setBook(shoppingCartDTO.getBook());
            itemList.add(orderedItem);
            this.shoppingCartService.delete(shoppingCartDTO);
        }
        OrderedDTO orderedDTO = new OrderedDTO();
        orderedDTO.setOrderedDate(new Timestamp(new Date().getTime()));
        orderedDTO.setReceivedDate(null);
        orderedDTO.setStatus("ĐANG XỬ LÝ");
        orderedDTO.setTotalPrice(total_price + 25000);
        orderedDTO.setName(orderedInput.getName());
        orderedDTO.setPhone(orderedInput.getPhone());
        orderedDTO.setAddress(orderedInput.getAddress());
        orderedDTO.setUser(this.userService.findByEmail(principal.getName()));

        OrderedDTO resultOrdered = this.orderedService.save(orderedDTO);

        for (OrderedItemDTO orderedItem : itemList) {
            orderedItem.setOrdered(resultOrdered);
            this.orderItemService.save(orderedItem);
            ProductDTO book = orderedItem.getBook();
            book.setQuantity(book.getQuantity() - orderedItem.getQuantity());
            this.bookService.save(book);
        }
        return new ModelAndView("web/dat-hang-thanh-cong.html");
    }

    @PostMapping("/thanh-toan")
    public ModelAndView postPayment(@ModelAttribute("orderedInput") OrderedInput orderedInput, Principal principal) {
        if (principal == null) {
            return new ModelAndView("web/dang-nhap.html");
        }
        CartOutput cartOutput = new CartOutput();
        double total = 0;
        for (Long s : orderedInput.getCarts()) {
            ShoppingCartDTO shoppingCartDTO = this.shoppingCartService.getId(s);
            total += shoppingCartDTO.getBook().getPrice() * (1 - shoppingCartDTO.getBook().getDiscount() / 100) * shoppingCartDTO.getQuantity();
            cartOutput.getList().add(shoppingCartDTO);
        }
        cartOutput.setTotal(total);
        ModelAndView mav = new ModelAndView("web/thanh-toan.html");
        mav.addObject("output", cartOutput);
        UserDTO user = this.userService.findByEmail(principal.getName());
        user.setPassword("");
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping("/don-hang")
    public List<OrderedDTO> getOrdered(Principal principal) {
        return this.orderedService.findAllOrder(principal.getName());
    }

    @GetMapping("/thanh-toan")
    public ModelAndView getPayment(@RequestParam(name = "id", required = false, defaultValue = "null") Long id, Principal principal) {
        if (principal == null) {
            return new ModelAndView("web/dang-nhap.html");
        } else {
            ShoppingCartDTO temp = this.shoppingCartService.addProduct(id, principal.getName());
            this.shoppingCartService.updateQuantity(temp.getBook().getId(), 1, principal.getName());
            ShoppingCartDTO dto = this.shoppingCartService.getId(temp.getId());
            CartOutput cartOutput = new CartOutput();
            cartOutput.getList().add(dto);
            cartOutput.setTotal(dto.getBook().getPrice() * (1 - dto.getBook().getDiscount() / 100));
            ModelAndView mav = new ModelAndView("web/thanh-toan.html");
            mav.addObject("output", cartOutput);
            UserDTO user = this.userService.findByEmail(principal.getName());
            user.setPassword("");
            mav.addObject("user", user);
            return mav;
        }
    }

}
