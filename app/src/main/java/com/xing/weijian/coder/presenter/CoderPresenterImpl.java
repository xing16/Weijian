package com.xing.weijian.coder.presenter;

import android.view.View;

import com.xing.weijian.base.mvp.BasePresenter;
import com.xing.weijian.base.mvp.OnModelCallback;
import com.xing.weijian.coder.model.CoderBean;
import com.xing.weijian.coder.model.CoderModel;
import com.xing.weijian.coder.model.CoderModelImpl;
import com.xing.weijian.coder.model.OnCoderListener;
import com.xing.weijian.coder.view.CoderView;
import com.xing.weijian.events.CoderListEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by Administrator on 2017/9/2.
 */

public class CoderPresenterImpl extends BasePresenter<CoderView> {

    private CoderModel coderModel;

    public CoderPresenterImpl() {
        coderModel = new CoderModelImpl();
    }

    public void loadData(String type, int pageSize, int curPage) {
        coderModel.loadData(type, pageSize, curPage);
    }

    public void registerEvents() {
        EventBus.getDefault().register(this);
    }

    public void unregisterEvents() {
        EventBus.getDefault().unregister(this);
    }


    /**
     * 请求到的数据返回时，通过 eventbus 传递时，需要携带 type,否则 viewpager 中的所有的 fragment 都将更新数据
     *
     * @param event
     */
    @Subscribe
    public void onCoderListEvent(CoderListEvent event) {
        if (isViewAttached()) {
            getView().refreshUI(event.getCoderBeanList(), event.getType());
            getView().dismissProgressBar(event.getType());
        }

    }

}
