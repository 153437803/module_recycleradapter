package com.demo.adapter.entity;


import com.demo.adapter.adapter.TransAdapter;

import lib.kalu.recyclerview.model.MultModel;
import lib.kalu.recyclerview.model.TransModel;

public class Level0Item extends TransModel<Level1Item> implements MultModel {
    public String title;
    public String subTitle;

    public Level0Item(String title, String subTitle) {
        this.subTitle = subTitle;
        this.title = title;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getMultType() {
        return TransAdapter.TYPE_LEVEL_0;
    }
}
