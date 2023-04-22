package br.com.onebox.app.entity;

import br.com.onebox.app.enums.TipoDescontoProdutoEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedidos")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produtos_id", nullable = false)
    private Produto produto;


    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

    @Column(name = "preco_unitario_itenspedido", precision = 10, scale = 2, nullable = false)
    private BigDecimal precoUnitario;

    @Column(name = "quantidade_itenspedido", nullable = false)
    private int quantidade;

    @Column(name = "desconto", precision = 10, scale = 2, nullable = false)
    private BigDecimal desconto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_desconto", nullable = false)
    private TipoDescontoProdutoEnum tipoDesconto;

    public ItemPedido() {
    }

    public ItemPedido(Produto produto, int quantidade, BigDecimal precoUnitario, TipoDescontoProdutoEnum tipoDesconto) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.tipoDesconto = tipoDesconto;
        this.desconto = calcularDesconto();
    }

    private BigDecimal calcularDesconto() {
        switch (tipoDesconto) {
            case PROMOCAO:
                return produto.getPrecoUnitario().multiply(BigDecimal.valueOf(0.1));
            case QUANTIDADE:
                return produto.getPrecoUnitario().multiply(BigDecimal.valueOf(0.2));
            default:
                return BigDecimal.ZERO;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }


    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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

    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + id +
                ", produto=" + produto +
                ", precoUnitario=" + precoUnitario +
                ", quantidade=" + quantidade +
                ", desconto=" + desconto +
                ", tipoDesconto=" + tipoDesconto +
                '}';
    }
}
