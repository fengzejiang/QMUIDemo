package com.bqt.test.qmui;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.ArrayList;
import java.util.Arrays;

public class BottomSheet_TipDialogActivity extends ListActivity {
	private Context mcContext;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mcContext = this;
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
				dealTipDialog(position - 2);
				break;
		}
	}
	
	private void dealTipDialog(int position) {
		final QMUITipDialog tipDialog;
		switch (position) {
			case 0:
				tipDialog = new QMUITipDialog.Builder(mcContext)
						.setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
						.setTipWord("正在加载")
						.create();
				break;
			case 1:
				tipDialog = new QMUITipDialog.Builder(mcContext)
						.setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
						.setTipWord("发送成功")
						.create();
				break;
			case 2:
				tipDialog = new QMUITipDialog.Builder(mcContext)
						.setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
						.setTipWord("发送失败")
						.create();
				break;
			case 3:
				tipDialog = new QMUITipDialog.Builder(mcContext)
						.setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO)
						.setTipWord("请勿重复操作")
						.create();
				break;
			case 4:
				tipDialog = new QMUITipDialog.Builder(mcContext)
						.setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
						.create();
				break;
			case 5:
				tipDialog = new QMUITipDialog.Builder(mcContext)
						.setTipWord("请勿重复操作")
						.create();
				break;
			case 6:
				tipDialog = new QMUITipDialog.CustomBuilder(mcContext)
						.setContent(R.layout.qmui_bottom_sheet_grid)
						.create();
				break;
			default:
				tipDialog = new QMUITipDialog.Builder(mcContext)
						.setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
						.setTipWord("正在加载")
						.create();
		}
		tipDialog.show();
		new Handler().postDelayed(tipDialog::dismiss, 1500);
	}
	
	private void showSimpleBottomSheetList() {
		new QMUIBottomSheet.BottomListSheetBuilder(mcContext)
				.addItem("Item 1")
				.addItem("Item 2")
				.addItem("Item 3")
				.setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
					dialog.dismiss();
					Toast.makeText(mcContext, "Item " + (position + 1), Toast.LENGTH_SHORT).show();
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
		new QMUIBottomSheet.BottomGridSheetBuilder(mcContext)
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
							Toast.makeText(mcContext, "分享到微信", Toast.LENGTH_SHORT).show();
							break;
						case TAG_SHARE_WECHAT_MOMENT:
							Toast.makeText(mcContext, "分享到朋友圈", Toast.LENGTH_SHORT).show();
							break;
						case TAG_SHARE_WEIBO:
							Toast.makeText(mcContext, "分享到微博", Toast.LENGTH_SHORT).show();
							break;
						case TAG_SHARE_CHAT:
							Toast.makeText(mcContext, "分享到私信", Toast.LENGTH_SHORT).show();
							break;
						case TAG_SHARE_LOCAL:
							Toast.makeText(mcContext, "保存到本地", Toast.LENGTH_SHORT).show();
							break;
					}
				})
				.build().show();
	}
}