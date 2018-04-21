package com.bqt.test.qmui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

public class QMUITabSegmentActivity extends Activity {
	private Context context;
	private QMUITopBar topBar;
	private QMUITabSegment tabSegment;
	private ViewPager viewPager;
	private Drawable drawable, drawable2;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_tab_viewpager_layout);
		context = this;
		drawable = ContextCompat.getDrawable(context, R.mipmap.ic_launcher);
		drawable2 = ContextCompat.getDrawable(context, R.mipmap.ic_launcher_round);
		
		topBar = findViewById(R.id.topbar);
		tabSegment = findViewById(R.id.tabSegment);
		viewPager = findViewById(R.id.contentViewPager);
		
		initView();
	}
	
	private void initView() {
		topBar.addLeftBackImageButton().setOnClickListener(v -> finish());
		topBar.setTitle("QMUITabSegment");
		topBar.addRightImageButton(R.mipmap.ic_launcher_round, R.id.topbar_right).setOnClickListener(v -> showBottomSheetList());
		
		viewPager.setAdapter(new PagerAdapter() {
			@Override
			public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
				return view == object;
			}
			
			@Override
			public int getCount() {
				return 2;
			}
			
			@NonNull
			@Override
			public Object instantiateItem(@NonNull final ViewGroup container, int position) {
				TextView textView = new TextView(context);
				textView.setGravity(Gravity.CENTER);
				textView.setBackgroundColor(position == 0 ? Color.YELLOW : Color.CYAN);
				textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
				textView.setText(position == 0 ? "白乾涛" : "包青天  " + position);
				container.addView(textView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
				return textView;
			}
			
			@Override
			public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
				container.removeView((View) object);
			}
		});
		viewPager.setCurrentItem(0, false);
		
		tabSegment.addTab(new QMUITabSegment.Tab("白乾涛"));
		tabSegment.addTab(new QMUITabSegment.Tab("包青天"));
		tabSegment.setupWithViewPager(viewPager, false);
		tabSegment.setMode(QMUITabSegment.MODE_FIXED);
		/*tabSegment.setMode(QMUITabSegment.MODE_SCROLLABLE);
		tabSegment.setItemSpaceInScrollMode(QMUIDisplayHelper.dp2px(this, 16));*/
		
		tabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
			@Override
			public void onTabSelected(int index) {//当某个 Tab 被选中时会触发
				Log.i("bqt", "【onTabSelected】" + index);
				tabSegment.hideSignCountView(index);//根据 index 在对应的 Tab 上隐藏红点
			}
			
			@Override
			public void onTabReselected(int index) {//当某个 Tab 处于被选中状态下再次被点击时会触发
				Log.i("bqt", "【onTabReselected】" + index);
				tabSegment.hideSignCountView(index);//根据 index 在对应的 Tab 上隐藏红点
			}
			
			@Override
			public void onTabUnselected(int index) {//当某个 Tab 被取消选中时会触发
				Log.i("bqt", "【onTabUnselected】" + index);
			}
			
			@Override
			public void onDoubleTap(int index) {//当某个 Tab 被双击时会触发
				Log.i("bqt", "【onDoubleTap】" + index);
			}
		});
	}
	
	private void showBottomSheetList() {
		new QMUIBottomSheet.BottomListSheetBuilder(this)
				.addItem("简单文字")
				.addItem("文字 + 底部")
				.addItem("文字 + 顶部 indicator")
				.addItem("文字 + indicator 长度不要跟随内容长度")
				.addItem("文字 + icon(支持四个方向) + 自动着色选中态 icon")
				.addItem("显示红点")
				.addItem("选中态更换 icon")
				.addItem("不同 item，不同文字(icon)颜色")
				.addItem("根据 index 更新 tab 文案")
				.addItem("根据 index 完全替换 tab")
				.setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
					dialog.dismiss();
					switch (position) {
						case 0:
							tabSegment.reset();//1、清空已经存在的 Tab。
							tabSegment.setHasIndicator(false);//设置是否需要显示 indicator
							tabSegment.addTab(new QMUITabSegment.Tab("白乾涛"));// 2、重新 addTab添加新的 Tab
							tabSegment.addTab(new QMUITabSegment.Tab("包青天"));
							tabSegment.notifyDataChanged();//3、通过 notifyDataChanged() 通知变动
							break;
						case 1:
							tabSegment.reset();
							tabSegment.setHasIndicator(true);
							tabSegment.setIndicatorPosition(false);//true 时表示 indicator 位置在 Tab 的上方, false 时表示在下方
							tabSegment.setIndicatorWidthAdjustContent(true);//设置 indicator的宽度是否随内容宽度变化
							tabSegment.addTab(new QMUITabSegment.Tab("白乾涛"));
							tabSegment.addTab(new QMUITabSegment.Tab("包青天"));
							tabSegment.notifyDataChanged();
							break;
						case 2:
							tabSegment.reset();
							tabSegment.setHasIndicator(true);
							tabSegment.setIndicatorPosition(true);
							tabSegment.setIndicatorWidthAdjustContent(true);
							tabSegment.addTab(new QMUITabSegment.Tab("白乾涛"));
							tabSegment.addTab(new QMUITabSegment.Tab("包青天"));
							tabSegment.notifyDataChanged();
							break;
						case 3:
							tabSegment.reset();
							tabSegment.setHasIndicator(true);
							tabSegment.setIndicatorPosition(false);
							tabSegment.setIndicatorWidthAdjustContent(false);
							tabSegment.addTab(new QMUITabSegment.Tab("白乾涛"));
							tabSegment.addTab(new QMUITabSegment.Tab("包青天"));
							tabSegment.notifyDataChanged();
							break;
						case 4:
							tabSegment.reset();
							tabSegment.setHasIndicator(false);
							tabSegment.addTab(new QMUITabSegment.Tab(drawable, null, "白乾涛", true));
							tabSegment.addTab(new QMUITabSegment.Tab(drawable2, null, "包青天", true));
							tabSegment.notifyDataChanged();
							break;
						case 5:
							QMUITabSegment.Tab tab = tabSegment.getTab(0);
							tab.setSignCountMargin(0, -QMUIDisplayHelper.dp2px(context, 4));
							tab.showSignCountView(context, 10086);//显示 Tab 上的未读数或红点
							tabSegment.notifyDataChanged();
							break;
						case 6:
							tabSegment.reset();
							tabSegment.setHasIndicator(false);
							tabSegment.addTab(new QMUITabSegment.Tab(drawable, drawable2, "白乾涛", false));
							tabSegment.addTab(new QMUITabSegment.Tab(drawable, drawable2, "包青天", false));
							tabSegment.notifyDataChanged();
							break;
						case 7:
							tabSegment.reset();
							tabSegment.setHasIndicator(true);
							tabSegment.setIndicatorWidthAdjustContent(true);
							tabSegment.setIndicatorPosition(false);
							QMUITabSegment.Tab tab1 = new QMUITabSegment.Tab(drawable, null, "白乾涛", true);
							tab1.setTextColor(Color.GREEN, Color.RED);
							QMUITabSegment.Tab tab2 = new QMUITabSegment.Tab(drawable2, null, "包青天", true);
							tab2.setTextColor(Color.GREEN, Color.RED);
							tabSegment.addTab(tab1);
							tabSegment.addTab(tab2);
							tabSegment.notifyDataChanged();
							break;
						case 8:
							tabSegment.updateTabText(0, "动态更新文案");//改变 Tab 的文案
							tabSegment.notifyDataChanged();
							break;
						case 9:
							QMUITabSegment.Tab replaceTab = new QMUITabSegment.Tab(drawable, null, "动态更新", true);
							tabSegment.replaceTab(0, replaceTab);//整个 Tab 替换
							tabSegment.notifyDataChanged();
							break;
						default:
							break;
					}
				})
				.build()
				.show();
	}
}