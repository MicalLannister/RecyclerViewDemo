package com.cq.lannister.recyclerviewdome.model;

import com.cq.lannister.recyclerviewdome.model.entity.Brand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lannister on 2017/11/3.
 */

public class BrandModel {
    public List<Brand> brandList(){
        List<Brand> brands = new ArrayList<>();
        brands.add(new Brand("Adidas"));
        brands.add(new Brand("Nike"));
        brands.add(new Brand("Netfix"));
        brands.add(new Brand("Puma"));
        brands.add(new Brand("CNN"));
        brands.add(new Brand("Tencent"));
        brands.add(new Brand("FaceBook"));
        brands.add(new Brand("Google"));
        brands.add(new Brand("Alibaba"));
        return brands;
    }
}
