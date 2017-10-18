package idv.hsiehpinghan.springbootstarterwebassistant.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import idv.hsiehpinghan.springbootstarterwebassistant.configuration.SpringSecurityConfiguration;
import idv.hsiehpinghan.springbootstarterwebassistant.property.ConfigurationProperty;
import idv.hsiehpinghan.springbootstarterwebassistant.service.BasicService;
import idv.hsiehpinghan.springbootstarterwebassistant.service.UserService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(BasicController.class)
@Import(SpringSecurityConfiguration.class)
public class BasicControllerTest {
	private final String STRING = "string";
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ConfigurationProperty configurationProperty;
	@MockBean
	private BasicService service;
	@MockBean
	private UserDetailsService userDetailsService;
	@MockBean
	private UserService userService;

	@Test
	public void save() throws Exception {
		String expected = String.format("findByString/%s", STRING);
		mockMvc.perform(get("/basic/save").param("string", STRING)).andExpect(status().is3xxRedirection())
				.andExpect(header().string("Location", expected));
	}

	@Test
	public void findByString() throws Exception {
		String url = String.format("/basic/findByString/%s", STRING);
		mockMvc.perform(get(url).param("string", STRING)).andExpect(status().isOk())
				.andExpect(view().name("basic/result")).andExpect(model().attributeExists("entities"));
	}

}
