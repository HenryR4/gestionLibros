/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gestionLibros.service;

import com.example.gestionLibros.dto.LibroDTO;
import com.example.gestionLibros.dto.LibroResponseDTO;
import com.example.gestionLibros.entity.Libro;
import com.example.gestionLibros.exception.ResourceNotFoundException;
import com.example.gestionLibros.repository.LibroRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author hisco
 */
@Service
public class LibroServiceImpl implements LibroService {
    
    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<LibroResponseDTO> obtenerTodos() {
        return libroRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LibroResponseDTO obtenerPorId(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con id: " + id));
        return convertToDTO(libro);
    }

    @Override
    public LibroResponseDTO crear(LibroDTO libroDTO) {
        Libro libro = convertToEntity(libroDTO);
        Libro savedLibro = libroRepository.save(libro);
        return convertToDTO(savedLibro);
    }

    @Override
    public LibroResponseDTO actualizar(Long id, LibroDTO libroDTO) {
        Libro existingLibro = libroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con id: " + id));
        
        // Actualizar los campos
        existingLibro.setTitulo(libroDTO.getTitulo());
        existingLibro.setAutor(libroDTO.getAutor());
        existingLibro.setIsbn(libroDTO.getIsbn());
        existingLibro.setFechaPublicacion(libroDTO.getFechaPublicacion());

        Libro updatedLibro = libroRepository.save(existingLibro);
        return convertToDTO(updatedLibro);
    }

    @Override
    public void eliminar(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Libro no encontrado con id: " + id);
        }
        libroRepository.deleteById(id);
    }

    private LibroResponseDTO convertToDTO(Libro libro) {
        LibroResponseDTO dto = new LibroResponseDTO();
        dto.setId(libro.getId());
        dto.setTitulo(libro.getTitulo());
        dto.setAutor(libro.getAutor());
        dto.setIsbn(libro.getIsbn());
        dto.setFechaPublicacion(libro.getFechaPublicacion());
        return dto;
    }

    private Libro convertToEntity(LibroDTO dto) {
        Libro libro = new Libro();
        libro.setTitulo(dto.getTitulo());
        libro.setAutor(dto.getAutor());
        libro.setIsbn(dto.getIsbn());
        libro.setFechaPublicacion(dto.getFechaPublicacion());
        return libro;
    }
}
