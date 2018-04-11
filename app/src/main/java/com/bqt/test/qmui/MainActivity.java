package com.bqt.test.qmui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ListActivity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] array = {"QMUIDialog",
				"QMUIBottomSheet、QMUITipDialog",
				"",
				"",
				"",
				"",};
		setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(Arrays.asList(array))));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
			case 0:
				startActivity(new Intent(this, QMUIDialogActivity.class));
				break;
			case 1:
				startActivity(new Intent(this, BottomSheet_TipDialogActivity.class));
				break;
			case 2:
				startActivity(new Intent(this, QMUIDialogActivity.class));
				break;
		}
	}
}