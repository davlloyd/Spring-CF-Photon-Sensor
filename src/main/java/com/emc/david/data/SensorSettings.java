package com.emc.david.data;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sensorsettings")
public class SensorSettings {
	private int lowpoint = 100;
	private int highpoint = 700;

	public SensorSettings(int low, int high){
		this.lowpoint = low;
		this.highpoint = high;
	}
	
	public int getLogPoint(){
		return this.lowpoint;
	}

	public int getHighPoint(){
		return this.highpoint;
	}
}
