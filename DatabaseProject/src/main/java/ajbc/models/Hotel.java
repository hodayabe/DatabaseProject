package ajbc.models;

import java.util.List;

import org.bson.types.ObjectId;

public class Hotel {
	
	private ObjectId id;
	private String name;
	private Address adress;
	private int rank;
	private List<Room> rooms;
	private float pricePerNight;
	private List<Order> orders;
	private int numRooms;
	private int roomInUse;
	
	
	public Hotel(ObjectId id, String name, Address adress, int rank, List<Room> rooms, float pricePerNight,
			List<Order> orders, int numRooms, int roomInUse) {

		this.id = id;
		this.name = name;
		this.adress = adress;
		this.rank = rank;
		this.rooms = rooms;
		this.pricePerNight = pricePerNight;
		this.orders = orders;
		this.numRooms = numRooms;
		this.roomInUse = roomInUse;
	}
	
	public Hotel(String name, Address adress, int rank, List<Room> rooms, float pricePerNight,
			List<Order> orders, int numRooms, int roomInUse) {

		this.name = name;
		this.adress = adress;
		this.rank = rank;
		this.rooms = rooms;
		this.pricePerNight = pricePerNight;
		this.orders = orders;
		this.numRooms = numRooms;
		this.roomInUse = roomInUse;
	}

	public Hotel() {}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAdress() {
		return adress;
	}

	public void setAdress(Address adress) {
		this.adress = adress;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public float getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(float pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public int getNumRooms() {
		return numRooms;
	}

	public void setNumRooms(int numRooms) {
		this.numRooms = numRooms;
	}

	public int getRoomInUse() {
		return roomInUse;
	}

	public void setRoomInUse(int roomInUse) {
		this.roomInUse = roomInUse;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", adress=" + adress + ", rank=" + rank + ", rooms=" + rooms
				+ ", pricePerNight=" + pricePerNight + ", orders=" + orders + ", numRooms=" + numRooms + ", roomInUse="
				+ roomInUse + "]";
	}


}
