package br.com.onebox.app.dtos;

import br.com.onebox.app.entity.ItemPedido;
import br.com.onebox.app.entity.Pedido;
import br.com.onebox.app.entity.Produto;
import br.com.onebox.app.enums.TipoDescontoPedidoEnum;
import br.com.onebox.app.enums.TipoDescontoProdutoEnum;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ItemPedidoDTO {
    private Long id;
    private Pedido pedido;
    private BigDecimal desconto;
    private Long idProduto;
    private Integer quantidade;
    private Produto produto;
    private TipoDescontoProdutoEnum tipoDesconto;

    public ItemPedidoDTO(Pedido pedido, Long idProduto, Integer quantidade, Produto produto) {
        this.pedido = pedido;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public ItemPedidoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public TipoDescontoProdutoEnum getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(TipoDescontoProdutoEnum tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }
}

