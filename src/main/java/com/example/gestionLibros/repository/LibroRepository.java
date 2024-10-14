/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.gestionLibros.repository;

import com.example.gestionLibros.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hisco
 */
public interface LibroRepository extends JpaRepository<Libro, Long> {
}

    
