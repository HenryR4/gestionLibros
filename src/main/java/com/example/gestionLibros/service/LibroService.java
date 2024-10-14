/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.gestionLibros.service;

import com.example.gestionLibros.dto.LibroDTO;
import com.example.gestionLibros.dto.LibroResponseDTO;
import java.util.List;
/**
 *
 * @author hisco
 */
public interface LibroService {
      List<LibroResponseDTO> obtenerTodos();
    LibroResponseDTO obtenerPorId(Long id);
    LibroResponseDTO crear(LibroDTO libroDTO);
    LibroResponseDTO actualizar(Long id, LibroDTO libroDTO);
    void eliminar(Long id);
}
