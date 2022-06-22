package ajbc.crud;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.BsonField;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.InsertOneResult;

import ajbc.models.Customer;
import ajbc.models.Hotel;
import ajbc.models.Order;

import static com.mongodb.client.model.Updates.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static com.mongodb.client.model.Accumulators.sum;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Accumulators.first;

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

	public List<Hotel> getAllHotels(){
		return hotelsCollection.find().into(new ArrayList<>());
	}
	
	public List<Customer> getAllCustomers(){
		return customersCollection.find().into(new ArrayList<>());
	}
	
	public List<Order> getAllOrders(){
		return ordersCollection.find().into(new ArrayList<>());
	}
	
	public Hotel getHotelById(ObjectId id){
		return hotelsCollection.find(Filters.eq("_id",id)).first();
	}
	
	public Customer getCustomerById(ObjectId id){
		return customersCollection.find(Filters.eq("_id",id)).first();
	}
	
	
	
	// 1
	public List<Order> getOrdersByCustomerId(ObjectId customerId) {
		return ordersCollection.find(Filters.eq("customer_id", customerId)).into(new ArrayList<>());
	}

	// 2
	public List<Hotel> getHotelsByCity(String city) {
		return hotelsCollection.find(Filters.eq("address.city", city)).into(new ArrayList<>());
	}

	// 3
	public boolean isHotelAvailable(ObjectId hotelId, LocalDate date) {
		Hotel hotel = getHotelById(hotelId);
		Bson match = match(Filters.eq("hotel_id", hotelId));
		Bson match2 = match(Filters.gte("end_date", date));
		Bson match3 = match(Filters.lte("start_date", date));
	
		List<Order> orders = ordersCollection.aggregate(Arrays.asList(match,match2,match3)).into(new ArrayList<>());
		System.out.println( orders.size());
		return hotel.getRooms().size() > orders.size();
	}

	//4
	public InsertOneResult addOrder(Order order) {
		Hotel hotel = getHotelById(order.getHotelId());
		
		if (hotel.getPeopleInRoom() < order.getNumPeople()
				|| !isHotelAvailable(order.getHotelId(), order.getStartDate())) 
			return null;
		
		InsertOneResult result =null;
		
		order.setTotalPrice(order.getNumNights() * hotel.getPricePerNight());
		result = ordersCollection.insertOne(order);
		
		Bson updatehotel = addToSet("orders", order.getId());
		hotelsCollection.updateOne(Filters.eq("_id", hotel.getId()), updatehotel);

		Bson updatecustomer = addToSet("orders", order.getId());
		customersCollection.updateOne(Filters.eq("_id", order.getCustomerId()), updatecustomer);

		return result;
	}
	
	
	//5
	public void cancelOrder(ObjectId orderId){
		Order order = ordersCollection.findOneAndDelete(Filters.eq("_id", orderId));
		
		Bson updatehotel = pull("orders",order.getId());
		hotelsCollection.updateOne(Filters.eq("_id", order.getHotelId()), updatehotel);
		
		Bson  updatecustomer = pull("orders", order.getId());
		customersCollection.updateOne(Filters.eq("_id", order.getCustomerId()), updatecustomer);
	}
	
	
	//6
	public void sortHotelsByTotalIncome(){
		
		MongoCollection<Document> tempOrder = DB.getCollection("orders");
		
		Bson pipeline = lookup("hotels", "hotel_id", "_id", "hotel_orders");
		Bson group = group("$hotel_orders.name", sum("totalIncome", "$total_price"));
		Bson sort = sort(Sorts.descending("totalIncome"));
		Bson project = project(Projections.fields(Projections.excludeId(),Projections.include("totalIncome"),Projections.computed("name", "$_id")));
		List<Document> results = tempOrder.aggregate(Arrays.asList(pipeline,group,sort,project)).into(new ArrayList<>());
		results.forEach(printDocuments());
	}
	
	
	//7
	public void totalPricesOfAllOrders(){
		MongoCollection<Document> tempOrder = DB.getCollection("orders");
		
		Bson group = group(null, sum("total", "$total_price"));
		Bson project = project(Projections.fields(Projections.excludeId(),Projections.include("total")));
		List<Document> results = tempOrder.aggregate(Arrays.asList(group, project)).into(new ArrayList<>());
		results.forEach(printDocuments());
	}
	
	
	private static Consumer<Document> printDocuments() {
		return doc -> System.out.println(doc.toJson(JsonWriterSettings.builder().indent(true).build()));
	}
	

}