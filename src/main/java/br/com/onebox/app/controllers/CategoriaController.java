package br.com.onebox.app.controllers;

import br.com.onebox.app.entity.Categoria;
import br.com.onebox.app.dtos.CategoriaNomeDTO;
import br.com.onebox.app.exceptions.CategoriaInvalidaException;
import br.com.onebox.app.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> cadastrar(@RequestBody Categoria novaCategoria) {
        try {
            categoriaService.cadastrar(novaCategoria);
            return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
        } catch (CategoriaInvalidaException exception) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaNomeDTO> getById(@PathVariable Long id) {
        try {
            CategoriaNomeDTO categoria = categoriaService.getById(id);
            return ResponseEntity.ok(categoria);
        } catch (Exception getByIdException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/vendas")
    public List<Map<String, Object>> relatorioVendasPorCategoria() {
        return categoriaService.relatorioVendasPorCategoria();
    }

}