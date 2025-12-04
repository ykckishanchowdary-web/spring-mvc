package com.learning.spring_mvc;

import com.learning.spring_mvc.model.Product;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
//Static Import have to set manually
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;


// what is webenvironment tells us , we are going to call same url that are current application is running on and defined in spring configuration
@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.DEFINED_PORT)
class SpringMvcApplicationTests {

	private final RestTemplate restTemplate = new RestTemplate();


	@Disabled
	@Test
	void getSingleProductById() {
		String url = "https://localhost:8080/api/products/1";
		Map response = restTemplate.getForObject(url,Map.class);

		assertThat(response).isNotNull();
		assertThat(response.get("id")).isEqualTo(1);
		assertThat(response.get("name")).isEqualTo("Product 1");
	}

	@Disabled
	@Test
	void addProduct(){
		String url = "http://localhost:8080/api/products";
		Product newProduct = new Product(1,"apple");
		Product response = restTemplate.postForObject(url,newProduct, Product.class);
		assertThat(response).isNotNull();
	}

}
