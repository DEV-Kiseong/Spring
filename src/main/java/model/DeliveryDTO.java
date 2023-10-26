package model;

import java.util.Date;

public class DeliveryDTO {
	String deliveryNum;
	String purchaseNum;
	String deliveryState;
	Date deliveryDate;
	
	public String getDeliveryNum() {
		return deliveryNum;
	}
	public void setDeliveryNum(String deliveryNum) {
		this.deliveryNum = deliveryNum;
	}
	public String getPurchaseNum() {
		return purchaseNum;
	}
	public void setPurchaseNum(String purchaseNum) {
		this.purchaseNum = purchaseNum;
	}
	public String getDeliveryState() {
		return deliveryState;
	}
	public void setDeliveryState(String deliveryState) {
		this.deliveryState = deliveryState;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
}
