package com.cdweb.controller.web;

import com.cdweb.dto.BannerDTO;
import com.cdweb.entity.BannerEntity;
import com.cdweb.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BannerController {
    @Autowired
    private IBannerService bannerService;

    @GetMapping("/banner")
    public List<BannerDTO> getBanner() {
        return this.bannerService.findAll();
    }
}
