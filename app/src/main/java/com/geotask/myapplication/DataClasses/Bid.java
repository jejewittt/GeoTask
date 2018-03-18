package com.geotask.myapplication.DataClasses;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import java.util.Date;


/**
 * bid class for holding infomration about a bid
 */
@Entity(tableName = "bids")
public class Bid extends GTData {

	@ColumnInfo(name = "provider_id")
	private String providerID;
	@ColumnInfo
	private Double value;
	@ColumnInfo(name = "task_id")
	private String taskID;

	@Ignore
	public Bid(){};

	/**
	 *constructor class
	 * @param providerID
	 * @param value
	 * @param taskID
	 * no return
	 */
	public Bid(String providerID, Double value, String taskID) {
		super.setType(Bid.class.toString());
		this.providerID = providerID;
		this.value = value;
		this.taskID = taskID;
		super.setDate(new Date().getTime());
	}

	/**
	 *sets provider Id
	 * @param ProviderID
	 */
	public void setProviderID(String ProviderID) {
		this.providerID = ProviderID;
	}
	/**
	 *returns provider ID
	 * @return this.providerID
	 */
	public String getProviderID() {
		return this.providerID;
	}
	/**
	 *sets bid ammount
	 * @param Value
	 */
	public void setValue(Double Value) {
		this.value = Value;
	}
	/**
	 *gets the bid ammount
	 * @return Value
	 */
	public Double getValue() {
		return this.value;
	}
	/**
	 *sets the taskId that the task provider is bidding on
	 * @param TaskID
	 */
	public void setTaskID(String TaskID) {
		this.taskID = TaskID;
	}
	/**
	 *gets taskId that the task provider is bidding on
	 * @return taskID
	 */
	public String getTaskID() {
		return this.taskID;
	}

	/**
	 *returns all items together
	 * @return this.providerID + this.value + this.taskID
	 */
	@Override
	public String toString(){
		return this.providerID + this.value + this.taskID ;
	}


}
