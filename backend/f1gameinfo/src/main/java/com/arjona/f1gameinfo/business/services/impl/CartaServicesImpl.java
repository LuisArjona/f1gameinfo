package com.arjona.f1gameinfo.business.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.arjona.f1gameinfo.business.repositores.CartaUsuarioRepository;
import com.arjona.f1gameinfo.business.repositores.CircuitoRepository;
import com.arjona.f1gameinfo.business.repositores.PilotoRepository;
import com.arjona.f1gameinfo.business.services.CartaServices;
import com.arjona.f1gameinfo.security.integration.model.Usuario;
import com.arjona.f1gameinfo.security.integration.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

import com.arjona.f1gameinfo.business.model.CartaCompradaDTO;
import com.arjona.f1gameinfo.business.model.CartaUsuario;
import com.arjona.f1gameinfo.business.model.CartaUsuarioDTO;
import com.arjona.f1gameinfo.business.model.Circuito;
import com.arjona.f1gameinfo.business.model.Piloto;

/**
 * Servicio que gestiona la lógica
 * de la aplicación sobre las cartas
 */
@Service
public class CartaServicesImpl implements CartaServices{
	
	private UsuarioRepository usuarioRepository;
	
	private CircuitoRepository circuitoRepository;
	
	private PilotoRepository pilotoRepository;

	private CartaUsuarioRepository cartaUsuarioRepository;

	public CartaServicesImpl(UsuarioRepository usuarioRepository, CircuitoRepository circuitoRepository,
			PilotoRepository pilotoRepository, CartaUsuarioRepository cartaUsuarioRepository) {
		this.usuarioRepository = usuarioRepository;
		this.circuitoRepository = circuitoRepository;
		this.pilotoRepository = pilotoRepository;
		this.cartaUsuarioRepository = cartaUsuarioRepository;
	}

	@Transactional
	@Override
	public void actualizarCartas(Long id, Integer monedas, Long idPiloto, Long idCircuito) {
		if(! usuarioRepository.existsById(id))
			throw new IllegalStateException("Usuario no existente.");
		
		Usuario usuario = usuarioRepository.findById(id).get();
		usuario.setMonedas(monedas);
		
		if(idPiloto != null) {
			if(! pilotoRepository.existsById(idPiloto))
				throw new IllegalStateException("Piloto no existente.");
			Piloto piloto = pilotoRepository.findById(idPiloto).get();
			usuario.addPiloto(piloto);
		}
		
		if(idCircuito != null) {
			if(! circuitoRepository.existsById(idCircuito))
				throw new IllegalStateException("Circuito no existente.");
			Circuito circuito = circuitoRepository.findById(idCircuito).get();
			usuario.addCircuito(circuito);
		}
		
		usuarioRepository.save(usuario);
	}

	@Override
	public List<CartaUsuarioDTO> getAllDtos() {
		return cartaUsuarioRepository.getAllDtos();
	}

	@Override
	public void subirCarta(Long id, Integer valoracion, MultipartFile imagen) {
	    if (!usuarioRepository.existsById(id)) {
	        throw new IllegalStateException("Usuario no existente.");
	    }
	    if (valoracion > 99 || valoracion < 1) {
	        throw new IllegalStateException("La valoración debe estar entre 1 y 99.");
	    }

	    Usuario usuario = usuarioRepository.findById(id).get();

	    String rutaBase = System.getProperty("user.dir");
	    String nombreArchivo = imagen.getOriginalFilename();

	    if (nombreArchivo == null || nombreArchivo.trim().isEmpty()) {
	        throw new IllegalStateException("El archivo no tiene un nombre válido.");
	    }

	    String extension = "";
	    int index = nombreArchivo.lastIndexOf('.');
	    if (index > 0) {
	        extension = nombreArchivo.substring(index);

	    String nuevoNombreArchivo = id + "-" + String.format("%06d", (int) (Math.random() * 1000000)) + extension;
	    File file = new File(rutaBase + "/uploads/images/" + nuevoNombreArchivo);

	    while (file.exists()) {
	        nuevoNombreArchivo = id + "-" + String.format("%06d", (int) (Math.random() * 1000000)) + extension;
	        file = new File(rutaBase + "/uploads/images/" + nuevoNombreArchivo);
	    }

	    try {
	        imagen.transferTo(file);
	    } catch (IOException e) {
	        throw new IllegalStateException("Error con la imagen: " + e.getMessage());
	    }

	    CartaUsuario carta = new CartaUsuario();
	    carta.setUsuario(usuario);
	    carta.setValoracion(valoracion);
	    carta.setRutaImagen("/uploads/images/" + nuevoNombreArchivo);
	    cartaUsuarioRepository.save(carta);
	    }
	}

	@Override
	public CartaUsuarioDTO getCartaUsuario(Long id) {
		CartaUsuarioDTO carta = cartaUsuarioRepository.getCartaUsuarioFromUsuario(id)
				.orElseThrow(() -> new IllegalStateException("El usuario no tiene carta creada."));
		return carta;
	}

	@Override
	public CartaCompradaDTO getCartasCompradasFromUsuario(Long id) {
		if(! usuarioRepository.existsById(id))
			throw new IllegalStateException("Usuario no existente.");
		Set<Piloto> pilotos = usuarioRepository.findPilotosById(id);
		Set<Circuito> circuitos = usuarioRepository.findCircuitosById(id);
		return new CartaCompradaDTO(pilotos, circuitos);
	}
}
