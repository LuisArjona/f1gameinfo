package com.arjona.f1gameinfo.security.presentation.restcontrollers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.MySQLContainer;

import com.arjona.f1gameinfo.security.UsuarioDetails;
import com.arjona.f1gameinfo.security.UsuarioDetailsService;
import com.arjona.f1gameinfo.security.UtilsJWT;
import com.arjona.f1gameinfo.security.UtilsOTP;
import com.arjona.f1gameinfo.security.integration.model.Usuario;
import com.arjona.f1gameinfo.security.integration.repositories.UsuarioRepository;
import com.arjona.f1gameinfo.security.integration.services.UsuarioServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

import io.restassured.RestAssured;

/**
 * Testea {@link AutentificacionController}
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AutentificacionControllerTest {
	
	 @LocalServerPort
	 private Integer port;
	
	@Autowired
   protected MockMvc mockMvc;
   
   @Autowired
   protected ObjectMapper mapper;
   
   @MockitoBean
   private AuthenticationManager authenticationManager;

   @MockitoBean
   private UsuarioServices usuarioServices;
   
   @MockitoBean
   private UsuarioRepository usuarioRepository;
   
   @MockitoBean
	private UtilsOTP utilsOTP;
   
   @MockitoBean
   private UtilsJWT utilsJWT;
   
   @MockitoBean
   private UsuarioDetailsService usuarioDetailsService;
   
   @MockitoBean
   private GoogleAuthenticator gAuth;
   
   @MockitoBean
   private PasswordEncoder passwordEncoder;
   
   static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:9.2.0")
   		.withDatabaseName("f1gameinfo")
           .withUsername("user")
           .withPassword("password")
           .withInitScript("schema.sql");;
   
   @BeforeAll
   static void beforeAll() {
   	mysqlContainer.start();
   }

   @AfterAll
   static void afterAll() {
   	mysqlContainer.stop();
   }
   
   @BeforeEach
   void setUp() {
     RestAssured.baseURI = "http://localhost:" + port;
   }
   
   @DynamicPropertySource
   static void configureProperties(DynamicPropertyRegistry registry) {
     registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
     registry.add("spring.datasource.username", mysqlContainer::getUsername);
     registry.add("spring.datasource.password", mysqlContainer::getPassword);
   }

   @Test
   void testLoginOk() throws Exception {
	 	String username = "test@example.com";
        String password = "password";
        String otpCode = "123456";
        
	    Usuario user = new Usuario(1L, "test@example.com", "password", new HashSet<>(), new HashSet<>(), 0, "910121");

       Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
       UsuarioDetails usuarioDetails = new UsuarioDetails(user);

       when(usuarioRepository.findByUsername(username)).thenReturn(Optional.of(user));
       when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
       when(utilsJWT.generarJwt(authentication)).thenReturn("mockJwtToken");
       when(gAuth.authorize(any(String.class), any(Integer.class), any(Long.class))).thenReturn(true);
       when(utilsOTP.verifyCode(any(String.class), any(Integer.class))).thenReturn(true);
       when(usuarioDetailsService.loadUserByUsername(username)).thenReturn(usuarioDetails);

       mockMvc.perform(post("/autentificacion/login")
               .param("username", username)
               .param("password", password)
               .param("otp", otpCode))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.token").value("mockJwtToken"));
   }
   
	 @Test
	 void testLoginInvalidOtp() throws Exception {
		 	String username = "test@example.com";
	        String password = "password";
	        String otpCode = "123456";
	        
		    Usuario user = new Usuario(1L, "test@example.com", "password", new HashSet<>(), new HashSet<>(), 0, "910121");

	        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
	        UsuarioDetails usuarioDetails = new UsuarioDetails(user);

	        when(usuarioRepository.findByUsername(username)).thenReturn(Optional.of(user));
	        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
	        when(utilsJWT.generarJwt(authentication)).thenReturn("mockJwtToken");
	        when(gAuth.authorize(any(String.class), any(Integer.class), any(Long.class))).thenReturn(false);
	        when(utilsOTP.verifyCode(any(String.class), any(Integer.class))).thenReturn(false);
	        when(usuarioDetailsService.loadUserByUsername(username)).thenReturn(usuarioDetails);

	        mockMvc.perform(post("/autentificacion/login")
	                .param("username", username)
	                .param("password", password)
	                .param("otp", otpCode))
	                .andExpect(status().isUnauthorized());
	     
	 }
	 
		@Test
		void testRegistrar() throws Exception {
		    
		    Usuario user = new Usuario(1L, "test@example.com", "password", new HashSet<>(), new HashSet<>(), 0, "910121");
		    
		    GoogleAuthenticatorKey key = mock(GoogleAuthenticatorKey.class);
		    when(key.getKey()).thenReturn("910121");
		    String qr = "fakeQRCode";
		    
		    when(utilsOTP.generateKey()).thenReturn(key);
		    when(utilsOTP.generateQRCode(user.getUsername(), key)).thenReturn(qr);
		    when(usuarioServices.registrarUsuario(user.getUsername(), user.getPassword(), user.getSecreto())).thenReturn(user);
		    
		    String expectedJsonResponse = "{\"qr\":\"fakeQRCode\",\"secreto\":\"910121\"}";

		    MvcResult resultado = mockMvc.perform(post("/autentificacion/registro")
		            .contentType("application/json")
		            .content(expectedJsonResponse))
		            .andExpect(status().isOk())
		            .andReturn();

		}
}
