package com.example.gestionLibros.controller;



import com.example.gestionLibros.dto.LibroDTO;
import com.example.gestionLibros.dto.LibroResponseDTO;
import com.example.gestionLibros.service.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")
@Tag(name = "Libro", description = "API para gestionar libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los libros")
    public ResponseEntity<List<LibroResponseDTO>> obtenerTodos() {
        List<LibroResponseDTO> Libros = libroService.obtenerTodos();
        return ResponseEntity.ok(Libros);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un libro por su ID")
    public ResponseEntity<LibroResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo libro")
    public ResponseEntity<LibroResponseDTO> crear(@RequestBody LibroDTO libroDTO) {
        return new ResponseEntity<>(libroService.crear(libroDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un libro existente")
    public ResponseEntity<LibroResponseDTO> actualizar(@PathVariable Long id, @RequestBody LibroDTO libroDTO) {
        return ResponseEntity.ok(libroService.actualizar(id, libroDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un libro")
    @ApiResponse(responseCode = "204", description = "Libro eliminado exitosamente")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}