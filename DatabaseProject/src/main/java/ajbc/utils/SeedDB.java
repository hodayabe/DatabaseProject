package ajbc.utils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertManyResult;

import ajbc.models.Address;
import ajbc.models.Hotel;
import ajbc.models.HotelsName;
import ajbc.models.Order;
import ajbc.models.Room;

public class SeedDB {

	public static InsertManyResult createCustomersCollection(MongoDatabase DB) {
		MongoCollection<Document> customersCol = DB.getCollection("customers");
		InsertManyResult result = customersCol.insertMany(Arrays.asList(
				new Document("first_name", "Hodaya").append("last_name", "david").append("country", "Israel"),
				new Document("first_name", "Shira").append("last_name", "ben").append("country", "China"),
				new Document("first_name", "noam").append("last_name", "Levi").append("country", "Israel"),
				new Document("first_name", "hila").append("last_name", "David").append("country", "Japan")));

		return result;
	}
	

	public static InsertManyResult createHotelsCollection(MongoDatabase DB) {
		Address add1 = new Address("levona", 8, "jerusalem", "Israel");
		List<Room> rooms1 = Arrays.asList(new Room(101,true,2), new Room(102,false,2), new Room(103, false,2), new Room(104, true,2));
		Hotel hermoso = new Hotel(HotelsName.HERMOSO, add1, 4, rooms1, 350);
		
		Address add2 = new Address("yafo", 15, "jerusalem", "Israel");
		List<Room> rooms2 = Arrays.asList(new Room(1,true,4), new Room(2,true,4), new Room(3,false,4));
		Hotel lindo = new Hotel(HotelsName.LINDO, add2, 3, rooms2, 200);
		
		Address add3 = new Address("shazar",3, "Tiberias", "Israel");
		List<Room> rooms3 = Arrays.asList(new Room(1,true,3), new Room(2,false,3));
		Hotel bello = new Hotel(HotelsName.BELLO, add3, 5, rooms3, 700);
		
		MongoCollection<Document> hotelsCol = DB.getCollection("hotels");
		InsertManyResult result = hotelsCol.insertMany(Arrays.asList(
				new Document("name", hermoso.getName()).append("address", hermoso.getAddress())
				.append("rank", hermoso.getRank()).append("rooms", hermoso.getRooms())
				.append("price_per_night", hermoso.getPricePerNight()).append("orders", hermoso.getOrders()),
				
				new Document("name", lindo.getName()).append("address", lindo.getAddress())
				.append("rank", lindo.getRank()).append("rooms", lindo.getRooms())
				.append("price_per_night", lindo.getPricePerNight()).append("orders", lindo.getOrders()),
				
				new Document("name", bello.getName()).append("address", bello.getAddress())
				.append("rank", bello.getRank()).append("rooms", bello.getRooms())
				.append("price_per_night", bello.getPricePerNight()).append("orders", bello.getOrders())));
		
		return result;
	}

	
	
	public static InsertManyResult createOrdersCollection(MongoDatabase DB){
		ObjectId hermosoId = new ObjectId("62b20e4caf23cf599f52c56d");
		ObjectId lindoId = new ObjectId("62b20e4caf23cf599f52c56e");
		ObjectId belloId = new ObjectId("62b20e4caf23cf599f52c56f");
		
		ObjectId cust1Id = new ObjectId("62b20e4aaf23cf599f52c569");
		ObjectId cust2Id = new ObjectId("62b20e4aaf23cf599f52c56a");
		ObjectId cust3Id = new ObjectId("62b20e4aaf23cf599f52c56b");
		ObjectId cust4Id = new ObjectId("62b20e4aaf23cf599f52c56c");
		

		Order order1 = new Order(hermosoId, cust1Id, LocalDate.now(), LocalDate.of(2022, 8, 1), 3, 2);
		Order order2 = new Order(lindoId, cust2Id, LocalDate.now(), LocalDate.of(2019, 1, 1), 4, 4);
		Order order3 = new Order(belloId, cust3Id, LocalDate.now(), LocalDate.of(2020, 2, 5), 2, 3);
		Order order4 = new Order(belloId, cust4Id, LocalDate.now(), LocalDate.of(2022, 3, 16), 6, 1);
		
		
		MongoCollection<Document>ordersCol = DB.getCollection("orders");
		InsertManyResult result = ordersCol.insertMany(Arrays.asList(
				new Document("hotel_id", order1.getHotelId()).append("customer_id", order1.getCustomerId()).append("order_date", order1.getOrderDate())
				.append("num_nights", order1.getNumNights()).append("total_price", order1.getTotalPrice()).append("num_people", order1.getNumPeople()),
				
				new Document("hotel_id", order2.getHotelId()).append("customer_id", order2.getCustomerId()).append("order_date", order2.getOrderDate())
				.append("num_nights", order2.getNumNights()).append("total_price", order2.getTotalPrice()).append("num_people", order2.getNumPeople()),
				
				new Document("hotel_id", order3.getHotelId()).append("customer_id", order3.getCustomerId()).append("order_date", order3.getOrderDate())
				.append("num_nights", order3.getNumNights()).append("total_price", order3.getTotalPrice()).append("num_people", order3.getNumPeople()),
				
				new Document("hotel_id", order4.getHotelId()).append("customer_id", order4.getCustomerId()).append("order_date", order4.getOrderDate())
				.append("num_nights", order4.getNumNights()).append("total_price", order4.getTotalPrice()).append("num_people", order4.getNumPeople())
				));
		
		return result;
	}

}