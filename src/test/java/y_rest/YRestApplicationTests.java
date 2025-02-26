package y_rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class YRestApplicationTests {

	@Test
	void contextLoads() {

	}

	@Test 
	void integratingAndTestingAndDoingGoodThings() throws InterruptedException {
		System.out.println("STARTING IMPORTANT WORK");

		Thread.sleep(5000);

		System.out.println("BIG THINGS ARE HAPPENING");

		assertNotEquals("hello", "world");
	}

}
