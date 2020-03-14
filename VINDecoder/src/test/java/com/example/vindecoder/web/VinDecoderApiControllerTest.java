package com.example.vindecoder.web;


import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.altimetrik.VINDecoder.VinDecoderRestController;
import com.altimetrik.VINDecoder.VinDecoderService;
import com.altimetrik.VINDecoder.VinInformation;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VinDecoderRestController.class)
public class VinDecoderApiControllerTest {

	@MockBean
	private VinDecoderService vinDecoderService;

	@Autowired
	private MockMvc mvc;

	@Test
	public void getVinInformation() throws Exception {
		VinInformation vinInformation = new VinInformation();
		given(this.vinDecoderService.getVinInforamtion("JH4TB2H26CC000000")).willReturn(vinInformation);
		this.mvc.perform(get("/api/vin/JH4TB2H26CC000000"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.vin", is("JH4TB2H26CC000000")));
		verify(this.vinDecoderService).getVinInforamtion("JH4TB2H26CC000000");
	}

}
