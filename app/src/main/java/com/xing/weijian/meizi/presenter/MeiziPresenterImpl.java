package com.xing.weijian.meizi.presenter;

import com.xing.weijian.base.mvp.BasePresenter;
import com.xing.weijian.events.MeiziListEvent;
import com.xing.weijian.meizi.model.MeiziBean;
import com.xing.weijian.meizi.model.MeiziModel;
import com.xing.weijian.meizi.model.MeiziModelImpl;
import com.xing.weijian.meizi.view.MeiziView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by Administrator on 2017/9/24.
 */

public class MeiziPresenterImpl extends BasePresenter<MeiziView> {

    private MeiziModel meiziModel;

    public MeiziPresenterImpl() {
        meiziModel = new MeiziModelImpl();
    }

    public void loadData(int pageSize, int curPage) {
        meiziModel.loadData(pageSize, curPage);
    }

    public void registerEvents() {
        EventBus.getDefault().register(this);
    }

    public void unregisterEvents() {
        EventBus.getDefault().unregister(this);
    }


    @Subscribe
    public void onMeiziListEvent(MeiziListEvent event) {
        if (event == null) {
            return;
        }
        List<MeiziBean> meiziBeanList = event.getMeiziBeanList();
        if (isViewAttached()) {
            getView().refreshUI(meiziBeanList);
        }
    }


}
