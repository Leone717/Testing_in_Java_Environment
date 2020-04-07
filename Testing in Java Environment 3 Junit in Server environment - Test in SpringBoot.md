# Testing in Java Environment #3 - Test in SpringBoot

## JUnit in server environment

We will use the existing SpringBoot Application.

```
  View
  - HTML5 + CSS + JS

            Controller
            - traffic manager

                        Service
                        - calculations
                        - business logics
                                            Model
                                            - storing datas
```

Download the SpringBoot folder. (Spring_Boot_JUnit)

If we use Postman, and send Gyula, then this server accept and get the body of the request.(**@Postmapping("/")**)

## Sample Test

```java
package com.elsospring.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.elsospring.service.HomeService;

@RunWith(SpringRunner.class)
WebMvcTest(value=HomeController.class,secure = false)
public class HomeController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private HomeService mockedSpyService;

	@Test
	public void testIndex() throws Exception {
		String inputName = "Kornél";

		String URI = "/";
		Mockito.when(mockedSpyService.nameCheck(Mockito.any(String.class))).thenReturn("ok");

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.TEXT_PLAIN).content(inputName)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAstring();
		assertThat(outputInJson).isEqualTo("ok");
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

}
```
Earlier we checked this server with Postman on localhost:8080, get back Gyula in the body.

*We send an imitated request and check the response by JUnit.*

**requestbuilder**: an object which help to create a *request* as Postman

We send a request to URI(which is "/")    
    send a simple text in the body(inputname):

```java
RequestBuilder requestBuilder = MockMvcRequestBuilders
               .post(URI) //lekerdezés(URI=/)
               .accept(MediaType.TEXT_PLAIN).content(inputName)
               .contentType(MediaType.APPLICATION_JSON);
```

Mockito check this is a String, if yes, it sends ok:

```java    
Mockito.when(mockedSpyService.nameCheck(Mockito.any(String.class))).thenReturn("ok");
```

We have dependency injetction:

```java
@Autowired
    private MockMvc mockMvc;
```

and we have a **MockInjection:**
```java
@MockBean
    private HomeService mockedSpyService;
```
**We use @MockBean to inject a Mock object into our test.**

We send the request and save the response of the request into **result**. After with the **getResponse()** get the response and save it into **repsonse**:
```java
MvcResult result = mockMvc.perform(requestBuilder).andReturn();
MockHttpServletResponse response = result.getResponse();
```
After save it as String and compare with **isEqualTo()** is match by "ok:
```java
String outputInJson = response.getContentAstring();
assertThat(outputInJson).isEqualTo("ok");
assertEquals(HttpStatus.OK.value(), response.getStatus());
```

```
  View
  - HTML5 + CSS + JS
                                MockMVC
      Controller  <------------RequestBuilder---JUnit
      - traffic manager ---------------------->
                          We check the answer

                        Service
                        - calculations
                        - business logics
                                            Model
                                            - storing datas
```
