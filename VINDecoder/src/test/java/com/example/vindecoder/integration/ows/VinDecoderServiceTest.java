package com.example.vindecoder.integration.ows;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import com.altimetrik.VINDecoder.VinDecoderService;
import com.altimetrik.VINDecoder.VinInformation;

@RunWith(SpringRunner.class)
@RestClientTest(VinDecoderService.class)
//@TestPropertySource(properties = "app.vindecoder.api.key=test-ABC")
public class VinDecoderServiceTest {

	private static final String VIN_SERVICE_URL = "https://vpic.nhtsa.dot.gov/api/vehicles/DecodeVinExtended/";

	@Autowired
	private VinDecoderService vinDecoderService;

	@Autowired
	private MockRestServiceServer server;

	@Test
	public void getVinInformation() {
		this.server.expect(
				requestTo(VIN_SERVICE_URL + "WAUFFAFM3CA000000?format=json"))
				.andRespond(withSuccess(
						new ClassPathResource("vin-decoder-test.json.json", getClass()),
						MediaType.APPLICATION_JSON));
		
		VinInformation vinInformation = this.vinDecoderService.getVinInforamtion("WAUFFAFM3CA000000");
		//assertTrue(vinInformation.getMake().isEqualTo("2012"));
		assertTrue(vinInformation.getMake().equalsIgnoreCase("2012"));
		
		/*
		 * Weather forecast = this.weatherService.getWeather("es", "barcelona");
		 * assertThat(forecast.getName()).isEqualTo("Barcelona");
		 * assertThat(forecast.getTemperature()).isEqualTo(286.72, Offset.offset(0.1));
		 * assertThat(forecast.getWeatherId()).isEqualTo(800);
		 * assertThat(forecast.getWeatherIcon()).isEqualTo("01d"); this.server.verify();
		 */
	}

}
