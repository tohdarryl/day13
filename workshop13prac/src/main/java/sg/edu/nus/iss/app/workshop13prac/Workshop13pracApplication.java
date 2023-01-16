package sg.edu.nus.iss.app.workshop13prac;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//Any static method that is inside IOUtil Class will be exposed as a methodin this current Class
import static sg.edu.nus.iss.app.workshop13prac.util.IOUtil.*;

@SpringBootApplication
public class Workshop13pracApplication {

	public static void main(String[] args) {
		//SpringApplication.run(Workshop13pracApplication.class, args);
		SpringApplication app = new SpringApplication(Workshop13pracApplication.class);
		//Take default arguments from spring and merge with Java arguments
		ApplicationArguments appArgs = new DefaultApplicationArguments(args);
		String opsVal = appArgs.getOptionValues("dataDir").get(0);

		if(opsVal != null){
			//Using IOUtil.java method. Creates Directory when Server starts running
			createDir(opsVal);
		} else{
			//Kill the server and doesnt allow it to start (unsuccessful termination)
			System.exit(1);
		}

		app.run(args);
	}

}
