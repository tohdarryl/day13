package sg.edu.nus.iss.app.workshop13;

import java.util.List;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Any static method that is inside IOUtil Class will be exposed as a methodin this current Class
import static sg.edu.nus.iss.app.workshop13.util.IOUtil.*;
@SpringBootApplication
public class Workshop13Application {

	public static void main(String[] args) {
		//Needed to create new directory
		SpringApplication app = new SpringApplication(Workshop13Application.class);
		//Take default arguments from Spring and merge with Java arguments
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List<String> opsVal = appArgs.getOptionValues("dataDir");
		System.out.println("opsVal" + opsVal);

		if(null != opsVal){
			//Using IOUtil.java method. Creates Directory when Server starts running
			createDir((String)opsVal.get(0));
		}else{
			//kill the server and doesnt allow it to start
			System.exit(1);
		}

		app.run(args);
	}

}
