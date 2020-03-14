package com.altimetrik.VINDecoder;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VinDecoderService {

	private static final String VIN_DECODER_URL = "https://vpic.nhtsa.dot.gov/api/vehicles/DecodeVinExtended/";

	private static final Logger logger = LoggerFactory.getLogger(VinDecoderService.class);

	private final RestTemplate restTemplate;

	public VinDecoderService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Cacheable("vin")
	public VinInformation getVinInforamtion(String vin) {
		System.out.println(" received Info :: " + vin);
		VinInformation vinInformation = new VinInformation();
		URL url;
		Scanner sc = null;
		try {
			url = new URL(VIN_DECODER_URL + vin + "?format=json");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();

			System.out.println(" Response Code : " + responsecode);
			StringBuffer inline = new StringBuffer();
			if (responsecode != 200)
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			else {
				sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline.append(sc.nextLine());
				}
				// System.out.println(“\nJSON data in string format”);
				System.out.println(inline);

				JSONParser parse = new JSONParser();
				JSONObject jobj = (JSONObject) parse.parse(inline.toString());

				JSONArray jsonarr = (JSONArray) jobj.get("Results");
				System.out.println(jsonarr);

				for (int i = 0; i < jsonarr.size(); i++) {
					JSONObject jsonobj_1 = (JSONObject) jsonarr.get(i);

					if (jsonobj_1.get("Variable").equals("Make")) {
						vinInformation.setMake(jsonobj_1.get("Value").toString());
					}

					if (jsonobj_1.get("Variable").equals("Model Year")) {
						vinInformation.setModelYear(jsonobj_1.get("Value").toString());
					}
					
					if(jsonobj_1.get("Variable").equals("Plant City")) {
						vinInformation.setPlantCity(jsonobj_1.get("Value").toString());
						
					}
					
					if(jsonobj_1.get("Variable").equals("Plant Country")) {
						vinInformation.setPlantCountry(jsonobj_1.get("Value").toString());
						
					}
					
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}

		return vinInformation;
	}

}
