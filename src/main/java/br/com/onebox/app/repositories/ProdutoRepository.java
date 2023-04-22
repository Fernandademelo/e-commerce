package br.com.onebox.app.repositories;


import br.com.onebox.app.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Page<Produto> findAll(Pageable pageable);

    @Modifying
    @Query("UPDATE Produto p SET p.quantidadeEstoque = p.quantidadeEstoque - :quantidade WHERE p.id = :id")
    void atualizarQuantidadeEstoque(@Param("id") Long id, @Param("quantidade") int quantidade);

    @Query("SELECT p.categoria AS categoria, SUM(i.quantidade) AS quantidadeVendida, SUM(i.quantidade * i.precoUnitario) AS montanteVendido \n" +
            "FROM Pedido pe \n" +
            "JOIN pe.itens i \n" +
            "JOIN i.produto p \n" +
            "GROUP BY p.categoria\n")
    List<Object[]> vendasPorCategoria();

};