package com.cq.lannister.recyclerviewdome.model;

import com.cq.lannister.recyclerviewdome.model.entity.Cheese;

import java.util.ArrayList;
import java.util.List;

/**
 * create by lym on 2019/11/19.
 */
public class FakeCheeseDataSource {

    public List<Cheese> syncGetCheeses(int size){
        List<Cheese> cheeses = new ArrayList<>();
        for (int i = 0; i < size ; i++) {
            cheeses.add(Cheese.generateNew());
        }
        return cheeses;
    }

    public Cheese syncGetCheeses(){
        return Cheese.generateNew();
    }

}
