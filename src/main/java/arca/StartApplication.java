package arca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public
class StartApplication
{

	public static
	void main(String[] args)
	throws SQLException, ClassNotFoundException
	{
		SpringApplication.run(StartApplication.class, args);
		/*TestArca testArca = new TestArca();
		testArca.execute();*/
	}
}
