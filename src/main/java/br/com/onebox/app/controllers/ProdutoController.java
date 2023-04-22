package br.com.onebox.app.controllers;

import br.com.onebox.app.dtos.ProdutoDTO;
import br.com.onebox.app.entity.Produto;
import br.com.onebox.app.exceptions.CategoriaInvalidaException;
import br.com.onebox.app.exceptions.IdNaoEncontradoException;
import br.com.onebox.app.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO produtoCadastrado = produtoService.cadastrarProduto(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCadastrado);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> listarProdutos(@RequestParam(defaultValue = "0") int pagina) {
        Page<ProdutoDTO> produtos = produtoService.listarProdutos(pagina);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable Long id) {
        ProdutoDTO produtoDTO = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(produtoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDto) {
        try {
            Produto produtoAtualizado = produtoService.atualizarProduto(id, produtoDto);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (IdNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        } catch (CategoriaInvalidaException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

