package com.learning.springboot.springboote2e;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.learning.springboot.springboote2e.controller.UserController;
import com.learning.springboot.springboote2e.repository.UserRepository;

@SpringBootTest
class SpringBootE2eApplicationTests {
	
	// This class is just to show how to test controller methods, 
	//i.e., how to test rest services, 
	//but doesn't actually contains accurate code for testing any of the 
	//service in current controllers in this project

	@Test
	void contextLoads() {
	}
	
	@MockBean
    private UserRepository userRepository;
    
    @Autowired
    UserController userController;

    @Autowired
    private MockMvc mockMvc;
    
    
    @Test
    public void whenPostRequestToUsersAndValidUser_thenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        String user = "{\"name\": \"Mahesh Tallada\", \"salary\" : 35050.45,"
        		+ " \"email\": \"mahesh.tallada@gmail.com\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
          .content(user)
          .contentType(MediaType.APPLICATION_JSON_UTF8))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content()
            .contentType(textPlainUtf8));
    }

}
