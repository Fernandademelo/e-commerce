package br.com.onebox.app.service;

import br.com.onebox.app.dtos.ProdutoDTO;
import br.com.onebox.app.entity.Categoria;
import br.com.onebox.app.entity.Produto;
import br.com.onebox.app.exceptions.IdNaoEncontradoException;
import br.com.onebox.app.repositories.CategoriaRepository;
import br.com.onebox.app.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private final ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoDTO cadastrarProduto(ProdutoDTO produtoDTO) {
        Categoria categoria = categoriaRepository.findById(produtoDTO.getIdCategoria())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria Inválida"));

        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setPrecoUnitario(produtoDTO.getPrecoUnitario());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setQuantidadeEstoque(produtoDTO.getQuantidadeEstoque());
        produto.setCategoria(categoria);

        produtoRepository.save(produto);

        ProdutoDTO novoProdutoDTO = new ProdutoDTO();
        novoProdutoDTO.setNome(produto.getNome());
        novoProdutoDTO.setPrecoUnitario(produto.getPrecoUnitario());
        novoProdutoDTO.setDescricao(produto.getDescricao());
        novoProdutoDTO.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        novoProdutoDTO.setIdCategoria(produto.getCategoria().getId());
        novoProdutoDTO.setCategoriaNome(produto.getCategoria().getNome());

        return novoProdutoDTO;
    }


    public Page<ProdutoDTO> listarProdutos(int pagina) {
        Pageable pageable = PageRequest.of(pagina, 5, Sort.by("nome"));
        Page<Produto> produtos = produtoRepository.findAll(pageable);

        List<ProdutoDTO> produtosDTO = new ArrayList<>();
        for (Produto produto : produtos) {
            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.setNome(produto.getNome());
            produtoDTO.setPrecoUnitario(produto.getPrecoUnitario());
            produtoDTO.setDescricao(produto.getDescricao());
            produtoDTO.setQuantidadeEstoque(produto.getQuantidadeEstoque());
            produtoDTO.setIdCategoria(produto.getCategoria().getId());
            produtoDTO.setCategoriaNome(produto.getCategoria().getNome());
            produtosDTO.add(produtoDTO);
        }

        return new PageImpl<>(produtosDTO, pageable, produtos.getTotalElements());
    }

    public ProdutoDTO buscarProdutoPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setPrecoUnitario(produto.getPrecoUnitario());
        produtoDTO.setDescricao(produto.getDescricao());
        produtoDTO.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        produtoDTO.setIdCategoria(produto.getCategoria().getId());
        produtoDTO.setCategoriaNome(produto.getCategoria().getNome());

        return produtoDTO;
    }

    public Produto atualizarProduto(Long id, ProdutoDTO produtoAtualizado) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (!produtoOptional.isPresent()) {
            throw new IdNaoEncontradoException("Produto não encontrado");
        }
        Produto produtoExistente = produtoOptional.get();
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setPrecoUnitario(produtoAtualizado.getPrecoUnitario());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        produtoExistente.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());

        Categoria categoria = categoriaRepository.findById(produtoAtualizado.getIdCategoria())
                .orElseThrow(() -> new IdNaoEncontradoException("Categoria não encontrada"));
        produtoExistente.setCategoria(categoria);
        return produtoRepository.save(produtoExistente);
    }

}

