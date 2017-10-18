package idv.hsiehpinghan.springbootstarterwebassistant.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import idv.hsiehpinghan.springbootstarterwebassistant.configuration.SpringSecurityConfiguration;
import idv.hsiehpinghan.springbootstarterwebassistant.service.UserService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
@Import(SpringSecurityConfiguration.class)
public class AdminControllerTest {
	private final String LOGIN_PAGE_URL = "http://localhost/common/loginPage";
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserDetailsService userDetailsService;
	@MockBean
	private UserService userService;

	@Test
	public void index_witNoAuthorized() throws Exception {
		mockMvc.perform(get("/admin/index")).andExpect(status().is3xxRedirection())
				.andExpect(header().string("Location", LOGIN_PAGE_URL));
	}

	@Test
	@WithMockUser(roles = { "ADMIN" })
	public void index_withMockUser() throws Exception {
		mockMvc.perform(get("/admin/index")).andExpect(status().isOk()).andExpect(view().name("admin/index"));
	}

}
