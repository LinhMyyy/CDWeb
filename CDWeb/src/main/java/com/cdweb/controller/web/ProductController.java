package com.cdweb.controller.web;

import com.cdweb.api.web.output.ProductOutput;
import com.cdweb.dto.ProductDTO;
import com.cdweb.dto.UserDTO;
import com.cdweb.service.IProductService;
import com.cdweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private IProductService bookService;
    @Autowired
    private IUserService userService;

    //  ?category=lang-mang&page=1&limit=5&sort=name&order=asc
    @GetMapping("/danh-sach-san-pham")
    public ProductOutput listCustomer(
            @RequestParam(name = "category", required = false, defaultValue = "null") String category,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "limit", required = false, defaultValue = "12") Integer limit,
            @RequestParam(name = "sort", required = false, defaultValue = "id") String sortName,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") String sortBy,
            @RequestParam(name = "hot", required = false, defaultValue = "false") boolean hotProduct,
            @RequestParam(name = "new", required = false, defaultValue = "false") boolean newProduct,
            @RequestParam(name = "sale", required = false, defaultValue = "false") boolean sale,
            @RequestParam(name = "title", required = false, defaultValue = "null") String title
    ) {
        ProductOutput bookOutput = new ProductOutput();
        Sort sortable = null;

        if ("ASC".equalsIgnoreCase(sortBy)) {
            sortable = Sort.by(sortName).ascending();
        }
        if ("DESC".equalsIgnoreCase(sortBy)) {
            sortable = Sort.by(sortName).descending();
        }
        Pageable pageable = PageRequest.of(page - 1, limit, sortable);
        if (!"null".equalsIgnoreCase(title)) {
            bookOutput.setListResult(bookService.findByTitle(title, pageable));
            bookOutput.setPage(page);
            bookOutput.setTotalPage((int) Math.ceil((double) (bookService.countByTitle(title)) / limit));
        } else if (!"null".equalsIgnoreCase(category)) {
            bookOutput.setListResult(bookService.findByCategory(category, pageable));
            bookOutput.setPage(page);
            bookOutput.setTotalPage((int) Math.ceil((double) (bookService.countByCategory(category)) / limit));
        } else if (hotProduct) {
            bookOutput.setListResult(bookService.findByHot(pageable));
            bookOutput.setPage(page);
            bookOutput.setTotalPage((int) Math.ceil((double) (bookService.countByHot()) / limit));
        } else if (newProduct) {
            bookOutput.setListResult(bookService.findByNew(pageable));
            bookOutput.setPage(page);
            bookOutput.setTotalPage((int) Math.ceil((double) (bookService.countByNew()) / limit));
        } else if (sale) {
            bookOutput.setListResult(bookService.findByDiscount(pageable));
            bookOutput.setPage(page);
            int count = bookService.countByDiscount();
            bookOutput.setTotalPage((int) Math.ceil((double) (count) / limit));
        } else {
            bookOutput.setListResult(bookService.findAll(pageable));
            bookOutput.setPage(page);
            bookOutput.setTotalPage((int) Math.ceil((double) (bookService.countAll()) / limit));
        }
        return bookOutput;
    }

    @GetMapping("/san-pham")
    public ModelAndView productPage(Principal principal) {
        ModelAndView mav = new ModelAndView("web/san-pham.html");
        UserDTO userDTO;
        if (principal != null) {
            userDTO = this.userService.findByEmail(principal.getName());
        } else {
            userDTO = null;
        }
        mav.addObject("user", userDTO);
        return mav;
    }

    @GetMapping("/chi-tiet-san-pham")
    public ModelAndView bookDetailPage(Principal principal) {
        ModelAndView mav = new ModelAndView("web/chi-tiet-san-pham.html");
        UserDTO userDTO;
        if (principal != null) {
            userDTO = this.userService.findByEmail(principal.getName());
        } else {
            userDTO = null;
        }
        mav.addObject("user", userDTO);
        return mav;
    }


    @GetMapping("/thong-tin-san-pham")
    public ProductDTO bookDetailProduct(@RequestParam(name = "id") Long id) {
        return bookService.findById(id);
    }


    @PostMapping(value = "/save-book")
    public ProductDTO createBook(@RequestBody ProductDTO productDTO) {
        return bookService.save(productDTO);
    }


    @GetMapping("/autocomplete")
    public List<String> autoComplete(@RequestParam(name = "title") String title) {
        return this.bookService.autoCompleteTitle(title);
    }

}
