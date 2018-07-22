package com.xing.weijian.coder.view;

import com.xing.weijian.base.mvp.BaseView;
import com.xing.weijian.coder.model.CoderBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/2.
 */

public interface CoderView extends BaseView {

    void refreshUI(List<CoderBean> list, String type);

    void dismissProgressBar(String type);

}
