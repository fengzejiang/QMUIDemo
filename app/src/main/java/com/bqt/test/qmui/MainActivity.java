package com.bqt.test.qmui;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIKeyboardHelper;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ListActivity {
	private Context mcContext;
	private int mCurrentDialogStyle = R.style.QMUI_Dialog;
	private boolean b;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		QMUIStatusBarHelper.setStatusBarLightMode(this);
		mcContext = this;
		String[] array = new String[]{
				"消息类型对话框（蓝色按钮）",
				"消息类型对话框（红色按钮）",
				"消息类型对话框 (很长文案)",
				"菜单类型对话框",
				"带 Checkbox 的消息确认框",
				"单选菜单类型对话框",
				"多选菜单类型对话框",
				"多选菜单类型对话框(item 数量很多)",
				"带输入框的对话框",
				"高度适应键盘升降的对话框"
		};
		setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(Arrays.asList(array))));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		mCurrentDialogStyle = b ? com.qmuiteam.qmui.R.style.QMUI_Dialog : R.style.DialogTheme2;
		
		b = !b;
		switch (position) {
			case 0:
				showMessagePositiveDialog();
				break;
			case 1:
				showMessageNegativeDialog();
				break;
			case 2:
				showLongMessageDialog();
				break;
			case 3:
				showMenuDialog();
				break;
			case 4:
				showConfirmMessageDialog();
				break;
			case 5:
				showSingleChoiceDialog();
				break;
			case 6:
				showMultiChoiceDialog();
				break;
			case 7:
				showNumerousMultiChoiceDialog();
				break;
			case 8:
				showEditTextDialog();
				break;
			case 9:
				showAutoDialog();
				break;
			
		}
	}
	
	private void showMessagePositiveDialog() {
		new QMUIDialog.MessageDialogBuilder(mcContext)
				.setTitle("标题")
				.setMessage("确定要发送吗？")
				.addAction("取消", (dialog, index) -> dialog.dismiss())
				.addAction("确定", (dialog, index) -> {
					dialog.dismiss();
					Toast.makeText(mcContext, "发送成功", Toast.LENGTH_SHORT).show();
				})
				.create(mCurrentDialogStyle).show();
	}
	
	private void showMessageNegativeDialog() {
		new QMUIDialog.MessageDialogBuilder(mcContext)
				.setTitle("标题")
				.setMessage("确定要删除吗？")
				.addAction("取消", (dialog, index) -> dialog.dismiss())
				.addAction(0, "删除", QMUIDialogAction.ACTION_PROP_NEGATIVE, (dialog, index) -> {
					Toast.makeText(mcContext, "删除成功", Toast.LENGTH_SHORT).show();
					dialog.dismiss();
				})
				.create(mCurrentDialogStyle).show();
	}
	
	private void showLongMessageDialog() {
		new QMUIDialog.MessageDialogBuilder(mcContext)
				.setTitle("标题")
				.setMessage("这是一段很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长" +
						"很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很" +
						"很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很" +
						"很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很" +
						"长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长" +
						"很长很长很长很长很很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长" +
						"很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长" +
						"很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长" +
						"很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长" +
						"很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长" +
						"很长很长很长很长很长很长很长很长很长很长很长很长很长很长长很长的文案")
				.addAction("取消", (dialog, index) -> dialog.dismiss())
				.create(mCurrentDialogStyle).show();
	}
	
	private void showConfirmMessageDialog() {
		new QMUIDialog.CheckBoxMessageDialogBuilder(mcContext)
				.setTitle("退出后是否删除账号信息?")
				.setMessage("删除账号信息")
				.setChecked(true)
				.addAction("取消", (dialog, index) -> dialog.dismiss())
				.addAction("退出", (dialog, index) -> dialog.dismiss())
				.create(mCurrentDialogStyle).show();
	}
	
	private void showMenuDialog() {
		final String[] items = new String[]{"选项1", "选项2", "选项3"};
		new QMUIDialog.MenuDialogBuilder(mcContext)
				.addItems(items, (dialog, which) -> {
					Toast.makeText(mcContext, "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
					dialog.dismiss();
				})
				.create(mCurrentDialogStyle).show();
	}
	
	private void showSingleChoiceDialog() {
		final String[] items = new String[]{"选项1", "选项2", "选项3"};
		final int checkedIndex = 1;
		new QMUIDialog.CheckableDialogBuilder(mcContext)
				.setCheckedIndex(checkedIndex)
				.addItems(items, (dialog, which) -> {
					Toast.makeText(mcContext, "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
					dialog.dismiss();
				})
				.create(mCurrentDialogStyle).show();
	}
	
	private void showMultiChoiceDialog() {
		final String[] items = new String[]{"选项1", "选项2", "选项3", "选项4", "选项5", "选项6"};
		final QMUIDialog.MultiCheckableDialogBuilder builder = new QMUIDialog.MultiCheckableDialogBuilder(mcContext)
				.setCheckedItems(new int[]{1, 3})
				.addItems(items, (dialog, which) -> {
				});
		builder.addAction("取消", (dialog, index) -> dialog.dismiss());
		builder.addAction("提交", (dialog, index) -> {
			StringBuilder result = new StringBuilder("你选择了 ");
			for (int i = 0; i < builder.getCheckedItemIndexes().length; i++) {
				result.append("").append(builder.getCheckedItemIndexes()[i]).append("; ");
			}
			Toast.makeText(mcContext, result.toString(), Toast.LENGTH_SHORT).show();
			dialog.dismiss();
		});
		builder.create(mCurrentDialogStyle).show();
	}
	
	private void showNumerousMultiChoiceDialog() {
		final String[] items = new String[]{
				"选项1", "选项2", "选项3", "选项4", "选项5", "选项6",
				"选项7", "选项8", "选项9", "选项10", "选项11", "选项12",
				"选项13", "选项14", "选项15", "选项16", "选项17", "选项18"
		};
		final QMUIDialog.MultiCheckableDialogBuilder builder = new QMUIDialog.MultiCheckableDialogBuilder(mcContext)
				.setCheckedItems(new int[]{1, 3})
				.addItems(items, (dialog, which) -> {
				});
		builder.addAction("取消", (dialog, index) -> dialog.dismiss());
		builder.addAction("提交", (dialog, index) -> {
			StringBuilder result = new StringBuilder("你选择了 ");
			for (int i = 0; i < builder.getCheckedItemIndexes().length; i++) {
				result.append("").append(builder.getCheckedItemIndexes()[i]).append("; ");
			}
			Toast.makeText(mcContext, result.toString(), Toast.LENGTH_SHORT).show();
			dialog.dismiss();
		});
		builder.create(mCurrentDialogStyle).show();
	}
	
	private void showEditTextDialog() {
		final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(mcContext);
		builder.setTitle("标题")
				.setPlaceholder("在此输入您的昵称")
				.setInputType(InputType.TYPE_CLASS_TEXT)
				.addAction("取消", (dialog, index) -> dialog.dismiss())
				.addAction("确定", (dialog, index) -> {
					CharSequence text = builder.getEditText().getText();
					if (text != null && text.length() > 0) {
						Toast.makeText(mcContext, "您的昵称: " + text, Toast.LENGTH_SHORT).show();
						dialog.dismiss();
					} else {
						Toast.makeText(mcContext, "请填入昵称", Toast.LENGTH_SHORT).show();
					}
				})
				.create(mCurrentDialogStyle).show();
	}
	
	private void showAutoDialog() {
		QMAutoTestDialogBuilder autoTestDialogBuilder = (QMAutoTestDialogBuilder) new QMAutoTestDialogBuilder(mcContext)
				.addAction("取消", (dialog, index) -> dialog.dismiss())
				.addAction("确定", (dialog, index) -> {
					Toast.makeText(mcContext, "你点了确定", Toast.LENGTH_SHORT).show();
					dialog.dismiss();
				});
		autoTestDialogBuilder.create(mCurrentDialogStyle).show();
		QMUIKeyboardHelper.showKeyboard(autoTestDialogBuilder.getEditText(), true);
	}
	
	class QMAutoTestDialogBuilder extends QMUIDialog.AutoResizeDialogBuilder {
		private Context mContext;
		private EditText mEditText;
		
		public QMAutoTestDialogBuilder(Context context) {
			super(context);
			mContext = context;
		}
		
		public EditText getEditText() {
			return mEditText;
		}
		
		@Override
		public View onBuildContent(QMUIDialog dialog, ScrollView parent) {
			LinearLayout layout = new LinearLayout(mContext);
			layout.setOrientation(LinearLayout.VERTICAL);
			layout.setLayoutParams(new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			int padding = QMUIDisplayHelper.dp2px(mContext, 20);
			layout.setPadding(padding, padding, padding, padding);
			mEditText = new EditText(mContext);
			QMUIViewHelper.setBackgroundKeepingPadding(mEditText, QMUIResHelper.getAttrDrawable(mContext, R.attr.qmui_list_item_bg_with_border_bottom));
			mEditText.setHint("输入框");
			LinearLayout.LayoutParams editTextLP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, QMUIDisplayHelper.dpToPx(50));
			editTextLP.bottomMargin = QMUIDisplayHelper.dp2px(mcContext, 15);
			mEditText.setLayoutParams(editTextLP);
			layout.addView(mEditText);
			TextView textView = new TextView(mContext);
			textView.setLineSpacing(QMUIDisplayHelper.dp2px(mcContext, 4), 1.0f);
			textView.setText("观察聚焦输入框后，键盘升起降下时 dialog 的高度自适应变化。\n\n" +
					"QMUI Android 的设计目的是用于辅助快速搭建一个具备基本设计还原效果的 Android 项目，" +
					"同时利用自身提供的丰富控件及兼容处理，让开发者能专注于业务需求而无需耗费精力在基础代码的设计上。" +
					"不管是新项目的创建，或是已有项目的维护，均可使开发效率和项目质量得到大幅度提升。");
			textView.setTextColor(ContextCompat.getColor(mcContext, R.color.app_color_description));
			textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			layout.addView(textView);
			return layout;
		}
	}
	
}