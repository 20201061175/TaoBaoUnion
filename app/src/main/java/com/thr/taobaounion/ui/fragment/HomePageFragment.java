package com.thr.taobaounion.ui.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.thr.taobaounion.R;
import com.thr.taobaounion.base.BaseFragment;
import com.thr.taobaounion.model.domain.Categories;
import com.thr.taobaounion.model.domain.HomePagerContent;
import com.thr.taobaounion.presenter.ICategoryPagerPresenter;
import com.thr.taobaounion.presenter.ITicketPresenter;
import com.thr.taobaounion.ui.activity.TicketActivity;
import com.thr.taobaounion.ui.adapter.HomePagerContentAdapter;
import com.thr.taobaounion.ui.adapter.LooperPagerAdapter;
import com.thr.taobaounion.ui.custom.AutoLooperViewPager;
import com.thr.taobaounion.ui.custom.TbNestedSerollView;
import com.thr.taobaounion.utils.Constants;
import com.thr.taobaounion.utils.LogUtils;
import com.thr.taobaounion.utils.PresenterManager;
import com.thr.taobaounion.utils.SizeUtils;
import com.thr.taobaounion.utils.TicketUtil;
import com.thr.taobaounion.utils.ToastUtils;
import com.thr.taobaounion.view.ICategoryPageCallback;

import java.util.List;

import butterknife.BindView;

public class HomePageFragment extends BaseFragment implements ICategoryPageCallback, HomePagerContentAdapter.OnListItemClickListener, LooperPagerAdapter.OnLooperPageItemClickListener {

    private ICategoryPagerPresenter categoryPagerPresenter;
    private int materialId;
    private HomePagerContentAdapter mContentListAdapter;

    @BindView(R.id.home_pager_content_list)
    public RecyclerView mContentList;

    @BindView(R.id.looper_pager)
    public AutoLooperViewPager looperPgaer;
    private LooperPagerAdapter mLooperPagerAdapter;

    @BindView(R.id.home_pager_title)
    public TextView currentPageTitle;

    //??????????????????
    @BindView(R.id.looper_point_container)
    public LinearLayout looperPointContainer;

    //smartRefresh??????
    @BindView(R.id.home_pager_refresh)
    public SmartRefreshLayout homePagerRefresh;

    @BindView(R.id.home_pager_parent)
    public LinearLayout homePagerParent;

    //TbNestedSerollView ??????????????????,??????NestedSerollView???RecyclerView?????????
    @BindView(R.id.home_pager_nested_scroll)
    public TbNestedSerollView homePagerNestedScroll;

    @BindView(R.id.home_header_container)
    public LinearLayout homeHeaderContainer;


    public static HomePageFragment newInstance(Categories.DataBean category) {
        HomePageFragment homePagerFragment = new HomePageFragment();
        Bundle bundle = new Bundle();
        //???fragment???????????????bundle
        bundle.putString(Constants.KEY_HOME_PAGER_TITLE, category.getTitle());
        bundle.putInt(Constants.KEY_HOME_PAGER_ID, category.getId());
        homePagerFragment.setArguments(bundle);
        return homePagerFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        //?????????????????????
        looperPgaer.startLoop();
    }

    @Override
    public void onPause() {
        super.onPause();
        //??????????????????
        looperPgaer.stopLoop();
    }

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initView(View view) {
        //?????????????????????
        mContentList.setLayoutManager(new LinearLayoutManager(getContext()));
        mContentList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = 8;
                outRect.bottom = 8;
            }
        });
        //???????????????
        mContentListAdapter = new HomePagerContentAdapter();
        //???????????????
        mContentList.setAdapter(mContentListAdapter);
        //?????????????????????
        mLooperPagerAdapter = new LooperPagerAdapter();
        //????????????????????????
        looperPgaer.setAdapter(mLooperPagerAdapter);
        //??????Refresh????????????
        homePagerRefresh.setEnableRefresh(false);
        homePagerRefresh.setEnableLoadMore(true);
        homePagerRefresh.setEnableAutoLoadMore(true);
        homePagerRefresh.setEnableOverScrollDrag(true);
    }

    @Override
    protected void initPresenter() { //?????????Presenter
        categoryPagerPresenter = PresenterManager.getInstance().getCategoryPagerPresenter();
        categoryPagerPresenter.registerViewCallback(this);
    }

    @Override
    protected void initListener() {
        //?????????????????????
        mContentListAdapter.setOnListItemClickListener(this);
        mLooperPagerAdapter.setOnLooperPageItemClickListener(this);
        //??????RecyclerView?????? ?????????????????????????????????
        homePagerParent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (homeHeaderContainer != null && homePagerParent != null && homePagerNestedScroll !=null){
                    //????????????????????????????????????????????????????????????????????????TbNestedscrollView???????????????
                    int height = homeHeaderContainer.getMeasuredHeight();
                    LogUtils.d(this, "????????? : " + height);
                    homePagerNestedScroll.setHeaderHeight(height);

                    int measuredHeight = homePagerParent.getMeasuredHeight();
                    LogUtils.d(this, "measuredHeight: " + measuredHeight);
                    ViewGroup.LayoutParams layoutParams = mContentList.getLayoutParams();
                    layoutParams.height = measuredHeight;
                    mContentList.setLayoutParams(layoutParams);
                    if (measuredHeight != 0) {
                        homePagerParent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        });

        looperPgaer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if (mLooperPagerAdapter.getSize() == 0) {
                    return;
                }
                int targetPosition = position % mLooperPagerAdapter.getSize();
                //???????????????
                updateLooperIndicator(targetPosition);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        homePagerRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                LogUtils.d(this, "??????loadmore....");
                homePagerRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //???Presenter???????????????
                        if (categoryPagerPresenter != null) {
                            categoryPagerPresenter.loaderMore(materialId);
                        }
                    }
                }, 0);
            }
        });
    }

    private void updateLooperIndicator(int targetPosition) {
        ///???????????????
        if (looperPointContainer != null) {//????????????
            for (int i = 0; i < looperPointContainer.getChildCount(); i++) {
                View point = looperPointContainer.getChildAt(i);
                if (i == targetPosition) {//??????????????????????????????
                    point.setBackgroundResource(R.drawable.shapr_looper_point_selected);
                } else {
                    point.setBackgroundResource(R.drawable.shapr_looper_point_normal);
                }
            }
        }
    }

    @Override
    protected void loadData() {
        Bundle arguments = getArguments();
        String title = arguments.getString(Constants.KEY_HOME_PAGER_TITLE);
        materialId = arguments.getInt(Constants.KEY_HOME_PAGER_ID);
        LogUtils.d(this, "title: " + title + ", " + "materialId: " + materialId);
        //????????????
        if (categoryPagerPresenter != null) {
            categoryPagerPresenter.getContentByCategoryId(materialId);
        }
        if (currentPageTitle != null) {
            currentPageTitle.setText(title);
        }
    }

    @Override //??????presenter
    protected void release() {
        if (categoryPagerPresenter != null) {
            categoryPagerPresenter.unregisterViewCallback(this);
        }
    }

    @Override
    protected void onRetryClick() {
        categoryPagerPresenter.getContentByCategoryId(materialId);
    }

    public int getCategoryId() {
        return materialId;
    }

    //??????CallBack????????????
    @Override
    public void onContentLoaded(List<HomePagerContent.DataBean> contents) {
        //??????????????????
        mContentListAdapter.setData(contents);
        setUpState(State.SUCCESS);
    }

    @Override
    public void onNetworkError() {
        //????????????
        setUpState(State.ERROR);
    }

    @Override
    public void onLoading() {
        setUpState(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setUpState(State.EMPTY);
    }

    //loadmore
    @Override
    public void onLoaderMoreError() {
        ToastUtils.show("???????????????????????????");
        homePagerRefresh.finishLoadMore();
    }

    @Override
    public void onLoaderMoreEmpty() {
        ToastUtils.show("????????????????????????");
        homePagerRefresh.finishLoadMore();
    }

    @Override
    public void onLoaderMoreLoaded(List<HomePagerContent.DataBean> contents) {
        //???????????????????????????
        mContentListAdapter.addData(contents);
        homePagerRefresh.finishLoadMore();
        ToastUtils.show("?????????" + contents.size() + "?????????");
    }

    //?????????
    @Override
    public void onLopperListLoaded(List<HomePagerContent.DataBean> contents) {
        LogUtils.d(this, "looper: size :" + contents.size());
        mLooperPagerAdapter.setData(contents);
        //??????????????????
        looperPgaer.setCurrentItem((Integer.MAX_VALUE/2)/contents.size()*contents.size());
        //???????????????
        looperPointContainer.removeAllViews();
        //??????????????????????????????
        for (int i = 0; i < contents.size(); i++) {
            View point = new View(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                    (SizeUtils.dip2px(getContext(), 6), SizeUtils.dip2px(getContext(), 6));
            layoutParams.leftMargin = SizeUtils.dip2px(getContext(), 5);
            layoutParams.rightMargin = SizeUtils.dip2px(getContext(), 5);
            point.setLayoutParams(layoutParams);
            if (i == 0) {//??????????????????????????????
                point.setBackgroundResource(R.drawable.shapr_looper_point_selected);
            } else {
                point.setBackgroundResource(R.drawable.shapr_looper_point_normal);
            }
            looperPointContainer.addView(point);
        }
    }

    //???????????????????????????????????????????????????
    @Override
    public void onItemClick(HomePagerContent.DataBean item) {
        //???????????????
//        LogUtils.d(this, item.getTitle());
        TicketUtil.toTicketPage(getContext(), item);
    }

    @Override
    public void onLooperItemClick(HomePagerContent.DataBean item) {
//        LogUtils.d(this, item.getTitle());
        TicketUtil.toTicketPage(getContext(), item);
    }

//    private void handleItemClick(HomePagerContent.DataBean item) {
//        String title = item.getTitle();
//        String url = item.getCoupon_click_url(); //????????????
//        if (TextUtils.isEmpty(url)) {
//            url = item.getClick_url(); //????????????
//        }
//        String cover = item.getPict_url();
//        //??????ticketPresenter?????????
//        ITicketPresenter ticketPresenter = PresenterManager.getInstance().getTicketPresenter();
//        ticketPresenter.getTicket(title, url, cover);//?????????????????????????????????????????????presenter??????????????????????????????
//        startActivity(new Intent(getContext(), TicketActivity.class));
//
//    }
}
