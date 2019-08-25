package com.hackthe6ix.proprice;

import com.hackthe6ix.proprice.service.retail.RetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProPriceApplicationTests {

	@Autowired
	private RetailService retailService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testBestBuy(){
		try {
			retailService.queryBestBuy("nintendo switch");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
