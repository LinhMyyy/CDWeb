package com.cdweb.service;

import com.cdweb.dto.BannerDTO;

import java.util.List;

public interface IBannerService {
    List<BannerDTO> findAll();
}
