package com.duguang.baseanimation.ui.imitate.counter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;

public class MoreFunctionsActivity extends ListActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getListView().setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				@SuppressWarnings("unchecked")
				Map<String, String> map = (Map<String, String>)parent.getItemAtPosition(position);
				Intent intent = new Intent(MoreFunctionsActivity.this, CalculatorActivity.class);
				String function = null;
				if(map == null || (function = map.get("FuncName")) == null || (function = function.trim()).length() == 0) {
					return;
				}
				intent.putExtra("function", function);
				intent.putExtra("needLParenthesis", map.get("NeedLParenthesis"));
				MoreFunctionsActivity.this.setResult(1, intent);
				MoreFunctionsActivity.this.finish();
			}
			
		});
		this.setTheme(android.R.style.Theme_Black);
		List<Map<String, String>> content = initfunctions();
		
		SimpleAdapter adapter = new SimpleAdapter(this,
				content,
				android.R.layout.simple_list_item_2,
				new String[] {"FuncName", "FuncSample"},
				new int[] {android.R.id.text1, android.R.id.text2});
		
		setListAdapter(adapter);
	}
	
	private static List<Map<String, String>> initfunctions() {
		List<Map<String, String>> content = new ArrayList<Map<String,String>>();
		String[][] funcArray = {
				{"&", "按位与,示例:1&3","false"},{"|", "按位或,示例:1|3","false"},
				{"~", "按位非,示例:~1","false"},{"^", "按位异或,示例:2^3","false"},
				{"sin","正弦,示例:sin(30)","true"},{"asin","反正弦,示例:asin(0.5)","true"},
				{"cos","余弦,示例:cos(60)","true"},{"acos","反余弦,示例:acos(0.5)","true"},
				{"tan","正切,示例:tan(45)","true"},{"atan","反正切,示例:atan(0.5)","true"},
				{"abs","绝对值,示例:abs(-25)","true"},
				{"exp","e的n次幂,示例:exp(3.5)","true"},{"ln","对数,底数为e,示例:ln(9.8)","true"},
				{"log","对数,底数为10,示例:log(100)","true"},
				{"pow","pow(a,b)表示a的b次幂,示例:pow(4,5)","true"},
				};
		
		for(String[] func : funcArray) {
			if(func != null && func.length == 3) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("FuncName", func[0]);
				map.put("FuncSample", func[1]);
				map.put("NeedLParenthesis", func[2]);
				content.add(map);
			}
		}
		return content;
	}
}
