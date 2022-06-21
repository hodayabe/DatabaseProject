package ajbc.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

import com.mongodb.ConnectionString;

public class MyConnectionString {
	
	
	private static String propertiesFile="nosql.properties";
	
	public static ConnectionString uri() {
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.ERROR);
		Properties props = new Properties();
		FileInputStream fileStream=null;;
		
		try {
			fileStream =new FileInputStream(propertiesFile);
			props.load(fileStream);
			
			String user = props.getProperty("user");
			String password=props.getProperty("password");
			String cluster=props.getProperty("cluster");
			String server_addres=props.getProperty("server_addres");
			String param1=props.getProperty("param1");
			String param2=props.getProperty("param2");

			
			String uri= "mongodb+srv://%s:%s@%s.%s/?%s&%s&keepAlive=true&poolSize=30&autoReconnect=true&socketTimeoutMS=360000&connectTimeoutMS=360000".formatted(user,password,cluster,server_addres,param1,param2);
			return new ConnectionString(uri);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fileStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		return null;
	}

}
