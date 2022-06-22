package ajbc.runner;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import ajbc.crud.ReservationDAO;
import ajbc.models.Hotel;
import ajbc.models.Order;
import ajbc.utils.MyConnectionString;
import ajbc.utils.SeedDB;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class Runner {

	public static void main(String[] args) {

		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.ERROR);

		// prepare codec registry
		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(MyConnectionString.uri())
				// add codec registry
				.codecRegistry(codecRegistry).serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
				.build();

		try (MongoClient mongoClient = MongoClients.create(settings)) {

			// create DB and Collection
			MongoDatabase DB = mongoClient.getDatabase("booking_reservations");
//			SeedDB.createCustomersCollection(DB);
//			SeedDB.createHotelsCollection(DB);
//			SeedDB.createOrdersCollection(DB);

			ReservationDAO resDao = new ReservationDAO(DB);

//			System.out.println("\r-------------Q1---------------\r");
//			List<Order> ordersOfCustomer = resDao.getOrdersByCustomerId(new ObjectId("62b2be35ec4fa25fba4f4c3f"));
//			ordersOfCustomer.forEach(System.out::println);
//
//			System.out.println("\r-------------Q2---------------\r");
//			System.out.println("hotels in jerusalem: ");
//			List<Hotel> hotelsByCity = resDao.getHotelsByCity("jerusalem");
//			hotelsByCity.forEach(System.out::println);
			
			System.out.println("\r-------------Q6---------------\r");
			resDao.sortHotelsByTotalIncome();
			
			

//			System.out.println("\r-------------Q7---------------\r");
//			resDao.totalPricesOfAllOrders();
		}
	}

}