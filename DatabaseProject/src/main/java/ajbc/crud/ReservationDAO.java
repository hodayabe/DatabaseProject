package ajbc.crud;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;

import ajbc.models.Customer;
import ajbc.models.Hotel;
import ajbc.models.Order;

import static com.mongodb.client.model.Updates.*;

import java.time.LocalDate;
import java.util.List;

public class ReservationDAO {

	private MongoDatabase DB;
	private MongoCollection<Hotel> hotelsCollection;
	private MongoCollection<Customer> customersCollection;
	private MongoCollection<Order> ordersCollection;

	public ReservationDAO(MongoDatabase DB) {
		this.DB = DB;
		this.hotelsCollection = DB.getCollection("hotels", Hotel.class);
		this.customersCollection = DB.getCollection("customers", Customer.class);
		this.ordersCollection = DB.getCollection("orders", Order.class);
	}

	
	public Hotel getHotelById(ObjectId id) {
		Hotel current = hotelsCollection.find(Filters.eq("id", id)).first();
		return current;
	}

	public Customer getCustomerById(ObjectId id) {
		Customer current = customersCollection.find(Filters.eq("id", id)).first();
		return current;
	}



}