package com.xing.weijian.events;

import com.xing.weijian.meizi.model.MeiziBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/24.
 */

public class MeiziListEvent {

    private List<MeiziBean> meiziBeanList;

    public MeiziListEvent(List<MeiziBean> meiziBeanList) {
        this.meiziBeanList = meiziBeanList;
    }

    public List<MeiziBean> getMeiziBeanList() {
        return meiziBeanList;
    }


}
