package ajbc.models;

import java.time.LocalDate;

import org.bson.types.ObjectId;

public class Order {
	
	private ObjectId id;
	private ObjectId hotelId;
	private ObjectId customerId;
	private LocalDate orderDate;
	private LocalDate startDate;
	private int numOfNight;
	private float totalPrice;
	
	
	public Order(ObjectId id, ObjectId hotelId, ObjectId customerId, LocalDate orderDate, LocalDate startDate,
			int numOfNight, float totalPrice) {
		this.id = id;
		this.hotelId = hotelId;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.startDate = startDate;
		this.numOfNight = numOfNight;
		this.totalPrice = totalPrice;
	}
	
	public Order(ObjectId hotelId, ObjectId customerId, LocalDate orderDate, LocalDate startDate,
			int numOfNight, float totalPrice) {
		this.hotelId = hotelId;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.startDate = startDate;
		this.numOfNight = numOfNight;
		this.totalPrice = totalPrice;
	}

	public Order() {}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getHotelId() {
		return hotelId;
	}

	public void setHotelId(ObjectId hotelId) {
		this.hotelId = hotelId;
	}

	public ObjectId getCustomerId() {
		return customerId;
	}

	public void setCustomerId(ObjectId customerId) {
		this.customerId = customerId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getNumOfNight() {
		return numOfNight;
	}

	public void setNumOfNight(int numOfNight) {
		this.numOfNight = numOfNight;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", hotelId=" + hotelId + ", customerId=" + customerId + ", orderDate=" + orderDate
				+ ", startDate=" + startDate + ", numOfNight=" + numOfNight + ", totalPrice=" + totalPrice + "]";
	}
		
	
	
	
	
	

}
