package BusPooling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BusPoolingApplication {

//	private static org.apache.log4j.Logger log = Logger.getLogger(LoggingFilter.class);
//protected final Logger log = LoggerFactory.getLogger(getClass());
	public static void main(String[] args) {

//		log.debug("asd");
//		PropertyConfigurator.configure(args[0]);


		SpringApplication.run(BusPoolingApplication.class, args);
	}
}
