package com.elsospring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.elsospring.controller.HomeController;
import com.elsospring.service.HomeService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=HomeController.class,secure = false)
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private HomeService mockedHomeService;

	@Test
	public void testIndex() throws Exception {
		
		String URI = "/";
		String body = "Kornél";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.TEXT_PLAIN).content(body)
				.contentType(MediaType.TEXT_PLAIN);
		
		Mockito.when(mockedHomeService.nameCheck(Mockito.any(String.class))).thenReturn("ok");
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputString = response.getContentAsString();
		assertThat(outputString).isEqualTo("ok");
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	


}
