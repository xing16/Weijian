package com.xing.weijian.coder.model;

import java.util.List;

/**
 * Created by Administrator on 2017/9/3.
 */

public interface OnCoderListener {

    void onSuccess(List<CoderBean> result);

    void onFailed(String error);

}
