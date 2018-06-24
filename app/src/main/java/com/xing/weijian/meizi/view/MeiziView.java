package com.xing.weijian.meizi.view;

import com.xing.weijian.base.mvp.BaseView;
import com.xing.weijian.meizi.model.MeiziBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/24.
 */

public interface MeiziView extends BaseView {

    void refreshUI(List<MeiziBean> meiziBeanList);
}
