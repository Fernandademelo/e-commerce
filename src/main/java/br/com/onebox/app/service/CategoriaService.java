package br.com.onebox.app.service;
import br.com.onebox.app.dtos.CategoriaNomeDTO;
import br.com.onebox.app.exceptions.CategoriaInvalidaException;
import br.com.onebox.app.entity.Categoria;
import br.com.onebox.app.exceptions.IdNaoEncontradoException;
import br.com.onebox.app.repositories.CategoriaRepository;
import br.com.onebox.app.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    public void cadastrar(Categoria novaCategoria) throws CategoriaInvalidaException {
        if (novaCategoria == null) {
            throw new CategoriaInvalidaException("Categoria não pode ser NULA.");
        }
        if (novaCategoria.getNome() == null || novaCategoria.getNome().trim().isEmpty()) {
            throw new CategoriaInvalidaException("Nome não pode ser VAZIO ou NULO.");
        }if (novaCategoria.getNome().length() < 2) {
            throw new CategoriaInvalidaException("Nome deve ter pelo menos 2 caracteres.");
        }
        novaCategoria.setStatus(true);
        categoriaRepository.save(novaCategoria);
    }

    public CategoriaNomeDTO getById(Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            CategoriaNomeDTO categoriaNomeDTO = new CategoriaNomeDTO();
            categoriaNomeDTO.setNome(categoria.getNome());
            return categoriaNomeDTO;
        }

        throw new IdNaoEncontradoException("Não foi possível encontrar a categoria com o id informado");

    }
    public List<Map<String, Object>> relatorioVendasPorCategoria() {
        List<Object[]> vendasPorCategoria = produtoRepository.vendasPorCategoria();
        List<Map<String, Object>> relatorio = new ArrayList<>();
        for (Object[] venda : vendasPorCategoria) {
            Map<String, Object> registro = new HashMap<>();
            registro.put("categoria", venda[0]);
            registro.put("quantidadeVendida", venda[1]);
            registro.put("montanteVendido", venda[2]);
            relatorio.add(registro);
        }
        return relatorio;
    }
}