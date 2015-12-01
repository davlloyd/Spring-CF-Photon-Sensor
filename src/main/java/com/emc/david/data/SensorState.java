package com.emc.david.data;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SensorState {
	@Id
	private ObjectId id;
	
	private Date date;
	private String item;
	private Integer state;

	public SensorState(){}
	
	public SensorState(String item, Integer state){
		this.id = ObjectId.get();
		this.date = new Date();
		this.item = item;
		this.state = state;
	}

	public SensorState(String item, Integer state, Date date){
		this.id = ObjectId.get();
		this.date = date;
		this.item = item;
		this.state = state;
	}

	public SensorState(ObjectId id, String item, Integer state, Date date){
		this.id = id;
		this.date = date;
		this.item = item;
		this.state = state;
	}

	public ObjectId getId(){
		return id;
	}

	public void setId(ObjectId id){
		this.id = id;
	}

	public Date getDate(){
		return date;
	}

	public void setDate(Date date){
		this.date = date;
	}

	public String getItem(){
		return item;
	}

	public void setItem(String item){
		this.item = item;
	}
	
	public Integer getState(){
		return state;
	}

	public void setState(Integer state){
		this.state = state;
	}

    @Override
    public String toString() {
        return String.format("SensorState[id=%s,item=%s,state=%s,date=%s", this.id, this.item, this.state, this.date);
    }
}

