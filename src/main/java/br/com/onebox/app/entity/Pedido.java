package br.com.onebox.app.entity;

import br.com.onebox.app.enums.TipoDescontoPedidoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "data_pedidos", nullable = false)
    private LocalDate dataPedidos;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;


    @Column(name = "preco_pedidos", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;


    @Column(name = "quantidade_pedidos", nullable = false, length = 100)
    private int quantidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_desconto_pedidos",  length = 20)
    private TipoDescontoPedidoEnum tipoDescontoPedido;

    @OneToMany(mappedBy = "pedido")
    @JsonIgnoreProperties("pedido")
    private List<ItemPedido> itens = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataPedidos() {
        return dataPedidos;
    }

    public void setDataPedidos(LocalDate dataPedidos) {
        this.dataPedidos = dataPedidos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return cliente != null ? cliente.getPedidos().size() : 0;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public TipoDescontoPedidoEnum getTipoDescontoPedido() {
        return tipoDescontoPedido;
    }

    public void setTipoDescontoPedido(TipoDescontoPedidoEnum tipoDescontoPedido) {
        this.tipoDescontoPedido = tipoDescontoPedido;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public Pedido(Cliente cliente, BigDecimal preco, int quantidade, LocalDate data) {
        this.cliente = cliente;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dataPedidos = data;
    }


    public Pedido() {
        this.itens = new ArrayList<>();
    }
    public Long adicionarItem(ItemPedido item) {
        item.setPedido(this);
        this.itens.add(item);
        return item.getId();
    }


    public boolean isMaisBaratoQue(Pedido outroPedido) {
        return preco.compareTo(outroPedido.getPreco()) < 0;
    }

    public boolean isMaisCaroQue(Pedido outroPedido) {
        return preco.compareTo(outroPedido.getPreco()) > 0;
    }

    public BigDecimal getValorTotal() {
        BigDecimal valorTotal = itens.stream()
                .map(ItemPedido::getPrecoUnitario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (tipoDescontoPedido == TipoDescontoPedidoEnum.FIDELIDADE) {
            valorTotal = valorTotal.multiply(BigDecimal.valueOf(0.9)); // 10% de desconto para CLIENTES FIEIS
        }

        return valorTotal;
    }

    public int getQuantidadeTotalItens() {
        int quantidadeTotal = 0;
        for (ItemPedido item : itens) {
            quantidadeTotal += item.getQuantidade();
        }
        return quantidadeTotal;
    }

    @Override
    public String toString() {
        return "Pedido" +
                "\n" + " Cliente:" + cliente.getPrimeiroNome() +
                "\n" + " Preco:" + preco +
                "\n" + " Valor Total:" + getValorTotal() +
                "\n" + " Data:" + dataPedidos + "\n";
    }

}