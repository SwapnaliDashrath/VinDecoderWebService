package com.altimetrik.VINDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VinDecoderRestController {
	
	private static final String template = "Hello, %s!";
	
	@Autowired
	private final VinDecoderService vinDecoderService;
	
	public VinDecoderRestController(VinDecoderService vinDecoderService) {
		this.vinDecoderService = vinDecoderService;
	}

	@GetMapping("/greeting")
	public VinInformation greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new VinInformation("", String.format(template, name));
	}
	
	@GetMapping("/vin/{vin}")
	public VinInformation getVinInforation(@PathVariable(value = "vin") String vin) {
		return this.vinDecoderService.getVinInforamtion(vin);
	}

}
