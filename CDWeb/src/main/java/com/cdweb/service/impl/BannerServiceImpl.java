package com.cdweb.service.impl;

import com.cdweb.converter.BannerConverter;
import com.cdweb.dto.BannerDTO;
import com.cdweb.entity.BannerEntity;
import com.cdweb.repository.BannerRepository;
import com.cdweb.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BannerServiceImpl implements IBannerService {
    @Autowired
    private BannerRepository bannerRepository;
    @Autowired
    private BannerConverter bannerConverter;
    @Override
    public List<BannerDTO> findAll() {
        List<BannerDTO> results=new ArrayList<>();
        List<BannerEntity> bannerList= bannerRepository.findAll();
        for (BannerEntity banner:bannerList){
            results.add(this.bannerConverter.toDTO(banner));
        }
        return results;
    }




}
