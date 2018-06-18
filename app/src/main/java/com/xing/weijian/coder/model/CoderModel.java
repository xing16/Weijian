package com.xing.weijian.coder.model;

import com.xing.weijian.base.mvp.OnModelCallback;

import java.util.List;

/**
 * Created by Administrator on 2017/9/2.
 */

public interface CoderModel {

    void loadData(String type, int pageSize, int curPage);
}
