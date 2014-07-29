package com.google.chartlib;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class googlechartview extends WebView {
	
	
	private chartdata cdata;
    private String Visualization="PieChart";
	public googlechartview(Context context) {
		super(context);
		
		
	}
	public googlechartview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
	
	}
	public googlechartview(Context context, AttributeSet attrs) {
        super(context, attrs);
	
	}
	public void setcdata(chartdata cdata){
		this.cdata=cdata;
	}
	private void setSettings() {
		this.getSettings().setJavaScriptEnabled(true);
		this.requestFocusFromTouch();
		this.cancelLongPress();
		this.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				return true;
			}
		});
	}
	public void setVisualization(String Visualization){
		this.Visualization=Visualization;
	}
	public void exec(){
		setSettings();
		String data= getupperhtml()+ getcdata();
		String options="]);"
				+
        "var options = "+getoptions()+"var chart = new google.visualization."+Visualization+getlowerhtml();
		this.loadDataWithBaseURL("file:///android_asset/", data+options, "text/html", "utf-8", null);
		Log.i("html",data+options);
	}

	private String getoptions() {
	
		return cdata.getOptions();
	}


	private String getcdata() {
		String content="";
		ArrayList<data> tempdata=cdata.getData();
		for(int i=0;i<tempdata.size();i++){
				content +=convert(tempdata.get(i).getData()).concat(",");
			
		}
		content.charAt(content.length()-1);
		Log.i("cdata", content);
		return content;
	}

	private static String convert(Object[] objects) { 
	    StringBuilder sb = new StringBuilder();
	    for (int i=0;i<objects.length;i++) { 
	    	 if (objects[i] instanceof String)
	        sb.append('\'').append(objects[i]).append('\'').append(',');
	    	 else
	    		 sb.append(objects[i]).append(',');
	    	
	    }
	    if (objects.length != 0) sb.deleteCharAt(sb.length()-1);
	    return "["+ sb.toString()+"]";
	}
private String getupperhtml(){
	String content = "<html>"
            + "  <head>"
            + "    <script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>"
            +"<style> "
            +"div {"
            +"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"
            +"-webkit-appearance:none;"
            +"margin:auto;"
            +"}"
            +"div:focus {"
            +"outline: 0;"
            +"border:none;"
            +"color: rgba(0, 0, 0, 0);"
            +"}"
            +"</style>"
            + "    <script type=\"text/javascript\">"
            + "      google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});"
            + "      google.setOnLoadCallback(drawChart);"
            + "      function drawChart() {"
            + "        var data = google.visualization.arrayToDataTable([";
	return content;
}

private String getlowerhtml(){
	return "    (document.getElementById('chart_div'));"
			+"chart.draw(data, options);"
             + "      }"
             + "    </script>"
             + "  </head>"
             + "  <body>"
             + "    <div id=\"chart_div\" style=\"width: 100%; height: 100%;\"></div>"
             + "  </body>" + "</html>";
}
}
