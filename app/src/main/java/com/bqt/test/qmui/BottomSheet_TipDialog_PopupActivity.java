package com.bqt.test.qmui;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BottomSheet_TipDialog_PopupActivity extends ListActivity {
	private Context mContext;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		String[] array = {"BottomSheet List",
				"BottomSheet Grid",
				"Loading 类型提示框",
				"成功提示类型提示框",
				"失败提示类型提示框",
				"信息提示类型提示框",
				"单独图片类型提示框",
				"单独文字类型提示框",
				"自定义内容提示框",};
		setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(Arrays.asList(array))));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
			case 0:
				showSimpleBottomSheetList();
				break;
			case 1:
				showSimpleBottomSheetGrid();
				break;
			default:
				dealTipDialog(v, position - 2);
				break;
		}
	}
	
	private void dealTipDialog(View v, int position) {
		if (position % 2 == 0) {
			showListPopup(v, new Random().nextInt(3));
		} else {
			showNormalPopup(v, new Random().nextInt(3));
		}
		final QMUITipDialog tipDialog;
		switch (position) {
			case 0:
				tipDialog = new QMUITipDialog.Builder(mContext)
						.setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
						.setTipWord("正在加载")
						.create();
				break;
			case 1:
				tipDialog = new QMUITipDialog.Builder(mContext)
						.setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
						.setTipWord("发送成功")
						.create();
				break;
			case 2:
				tipDialog = new QMUITipDialog.Builder(mContext)
						.setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
						.setTipWord("发送失败")
						.create();
				break;
			case 3:
				tipDialog = new QMUITipDialog.Builder(mContext)
						.setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO)
						.setTipWord("请勿重复操作")
						.create();
				break;
			case 4:
				tipDialog = new QMUITipDialog.Builder(mContext)
						.setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
						.create();
				break;
			case 5:
				tipDialog = new QMUITipDialog.Builder(mContext)
						.setTipWord("请勿重复操作")
						.create();
				break;
			case 6:
				tipDialog = new QMUITipDialog.CustomBuilder(mContext)
						.setContent(R.layout.qmui_bottom_sheet_grid)
						.create();
				break;
			default:
				tipDialog = new QMUITipDialog.Builder(mContext)
						.setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
						.setTipWord("正在加载")
						.create();
		}
		tipDialog.show();
		new Handler().postDelayed(tipDialog::dismiss, 1500);
	}
	
	private void showSimpleBottomSheetList() {
		new QMUIBottomSheet.BottomListSheetBuilder(mContext)
				.addItem("Item 1")
				.addItem("Item 2")
				.addItem("Item 3")
				.setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
					dialog.dismiss();
					Toast.makeText(mContext, "Item " + (position + 1), Toast.LENGTH_SHORT).show();
				})
				.build()
				.show();
	}
	
	private void showSimpleBottomSheetGrid() {
		final int TAG_SHARE_WECHAT_FRIEND = 0;
		final int TAG_SHARE_WECHAT_MOMENT = 1;
		final int TAG_SHARE_WEIBO = 2;
		final int TAG_SHARE_CHAT = 3;
		final int TAG_SHARE_LOCAL = 4;
		new QMUIBottomSheet.BottomGridSheetBuilder(mContext)
				.addItem(R.mipmap.ic_launcher, "分享到微信", TAG_SHARE_WECHAT_FRIEND, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
				.addItem(R.mipmap.ic_launcher, "分享到朋友圈", TAG_SHARE_WECHAT_MOMENT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
				.addItem(R.mipmap.ic_launcher, "分享到微博", TAG_SHARE_WEIBO, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
				.addItem(R.mipmap.ic_launcher, "分享到私信", TAG_SHARE_CHAT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
				.addItem(R.mipmap.ic_launcher, "保存到本地", TAG_SHARE_LOCAL, QMUIBottomSheet.BottomGridSheetBuilder.SECOND_LINE)
				.setOnSheetItemClickListener((dialog, itemView) -> {
					dialog.dismiss();
					int tag = (int) itemView.getTag();
					switch (tag) {
						case TAG_SHARE_WECHAT_FRIEND:
							Toast.makeText(mContext, "分享到微信", Toast.LENGTH_SHORT).show();
							break;
						case TAG_SHARE_WECHAT_MOMENT:
							Toast.makeText(mContext, "分享到朋友圈", Toast.LENGTH_SHORT).show();
							break;
						case TAG_SHARE_WEIBO:
							Toast.makeText(mContext, "分享到微博", Toast.LENGTH_SHORT).show();
							break;
						case TAG_SHARE_CHAT:
							Toast.makeText(mContext, "分享到私信", Toast.LENGTH_SHORT).show();
							break;
						case TAG_SHARE_LOCAL:
							Toast.makeText(mContext, "保存到本地", Toast.LENGTH_SHORT).show();
							break;
					}
				})
				.build().show();
	}
	
	private void showNormalPopup(View v, int preferredDirection) {
		QMUIPopup mNormalPopup = new QMUIPopup(mContext, QMUIPopup.DIRECTION_NONE);
		TextView textView = new TextView(mContext);
		textView.setLayoutParams(mNormalPopup.generateLayoutParam(QMUIDisplayHelper.dp2px(mContext, 250),
				ViewGroup.LayoutParams.WRAP_CONTENT));
		textView.setLineSpacing(QMUIDisplayHelper.dp2px(mContext, 4), 1.0f);
		int padding = QMUIDisplayHelper.dp2px(mContext, 20);
		textView.setPadding(padding, padding, padding, padding);
		textView.setText("Popup 可以设置其位置以及显示和隐藏的动画");
		textView.setTextColor(ContextCompat.getColor(mContext, R.color.app_color_description));
		mNormalPopup.setContentView(textView);
		mNormalPopup.setOnDismissListener(() -> Toast.makeText(mContext, "onDismiss", Toast.LENGTH_SHORT).show());
		
		mNormalPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
		mNormalPopup.setPreferredDirection(preferredDirection);//QMUIPopup.DIRECTION_TOP、DIRECTION_BOTTOM、DIRECTION_NONE
		mNormalPopup.show(v);
	}
	
	private void showListPopup(View v, int preferredDirection) {
		String[] array = new String[]{
				"Item 1",
				"Item 2",
				"Item 3",
				"Item 4",
				"Item 5",
		};
		ArrayAdapter adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, new ArrayList<>(Arrays.asList(array)));
		QMUIListPopup mListPopup = new QMUIListPopup(mContext, QMUIPopup.DIRECTION_NONE, adapter);
		mListPopup.create(QMUIDisplayHelper.dp2px(mContext, 250), QMUIDisplayHelper.dp2px(mContext, 200),
				(adapterView, view, i, l) -> {
					Toast.makeText(mContext, "Item " + (i + 1), Toast.LENGTH_SHORT).show();
					mListPopup.dismiss();
				});
		mListPopup.setOnDismissListener(() -> Toast.makeText(mContext, "onDismiss", Toast.LENGTH_SHORT).show());
		
		mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
		mListPopup.setPreferredDirection(preferredDirection);
		mListPopup.show(v);
	}
}