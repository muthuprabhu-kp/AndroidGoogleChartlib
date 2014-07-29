package com.google.chartlib;

import java.util.ArrayList;
import java.util.HashMap;

public class chartdata {
	private String title;
	private ArrayList<data> cdata;
	private String options;
	
public chartdata() {
	
}

public void setChartData(ArrayList<data> cdata){
	this.cdata=cdata;
}

public ArrayList<data> getData() {
return cdata;	
}

public void setOptions(String options){
	this.options=options;
}

public String getOptions(){
	return options;
}

public void setTitle(String title){
	this.title=title;
}
public String getTitle(){
	return title;
}

}
