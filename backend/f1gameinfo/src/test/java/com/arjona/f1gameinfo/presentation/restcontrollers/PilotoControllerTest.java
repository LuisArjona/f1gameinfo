package com.arjona.f1gameinfo.presentation.restcontrollers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.MySQLContainer;

import com.arjona.f1gameinfo.business.model.Piloto;
import com.arjona.f1gameinfo.business.services.PilotoServices;
import com.arjona.f1gameinfo.security.UsuarioDetails;
import com.arjona.f1gameinfo.security.UsuarioDetailsService;
import com.arjona.f1gameinfo.security.UtilsJWT;
import com.arjona.f1gameinfo.security.integration.model.Usuario;
import com.arjona.f1gameinfo.security.integration.repositories.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.restassured.RestAssured;

/**
 * Testea {@link PilotoController}
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PilotoControllerTest {

	@LocalServerPort
	private Integer port;
	
	@Autowired
	private MockMvc mockMvc;
	
    @MockitoBean
    private PilotoServices pilotoServices;
    
    private PilotoController pilotoController;
    
    @MockitoBean
    private UtilsJWT utilsJWT;
    
    @MockitoBean
    private UsuarioRepository usuarioRepository;
    
    @MockitoBean
    private UsuarioDetailsService usuarioDetailsService;
	
    @Value("${f1gameinfo.secreto.jwt}")
    private String jwtSecret;
    
    @Value("${f1gameinfo.tiempo.expiracion.jwt}")
    private int jwtExpirationMs;
    
    private ObjectMapper mapper = new ObjectMapper();

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
      pilotoController = new PilotoController(pilotoServices);
    }
	
	@DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
      registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
      registry.add("spring.datasource.username", mysqlContainer::getUsername);
      registry.add("spring.datasource.password", mysqlContainer::getPassword);
    }
	
    @Test
	void testGetAll() throws Exception{
        this.mockFiltro();
		String jwtToken = "Bearer " + this.generateJwt();
		
		Piloto p1 = new Piloto();
		p1.setId(1L);
		Piloto p2 = new Piloto();
		p2.setId(2L);
		
		String esperado = mapper.writeValueAsString(Arrays.asList(p1, p2));
		
		when(pilotoServices.getAll()).thenReturn(Arrays.asList(p1, p2));
		
		MvcResult respuesta = mockMvc.perform(get("/pilotos")
        		.header("Authorization", jwtToken))
                .andExpect(status().isOk())
                .andReturn();
		
		assertEquals(esperado, respuesta.getResponse().getContentAsString());
	}
    
    private String generateJwt() {
    	Map<String,Object> claims = new HashMap<String, Object>();
        
        claims.put("username", "prueba1@prueba.com");
        return Jwts.builder()
                .setClaims(claims)
                .setSubject("1")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(decodificarSecreto())
                .compact();
    }
    
    /**
     * Mockea el comportamiento del filtro JWT
     */
    private void mockFiltro() {
	     Usuario user = new Usuario(1L, "prueba1@prueba.com", "password", new HashSet<>(), new HashSet<>(), 0, "secreto");
		 
		 UsuarioDetails usuarioDetails = new UsuarioDetails(user);
	     when(usuarioRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
	     when(usuarioDetailsService.loadUserByUsername(user.getUsername())).thenReturn(usuarioDetails);
	     when(utilsJWT.validarJwt(any(String.class))).thenReturn(true);
	     when(utilsJWT.getTokenUsername(any(String.class))).thenReturn(user.getUsername());
    }
    
    private Key decodificarSecreto() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

}
