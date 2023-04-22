package br.com.onebox.app.dtos;
import br.com.onebox.app.enums.TipoDescontoPedidoEnum;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.List;

public class PedidoDTO {
    @NotNull
    private LocalDate dataPedido;
    private List<ItemPedidoDTO> itens;
    private ClienteDTO cliente;
    private BigDecimal preco;
    private TipoDescontoPedidoEnum tipoDescontoPedido;
    private int quantidade;

    public PedidoDTO() {
    }

    public PedidoDTO(LocalDate dataPedido, List<ItemPedidoDTO> itens, ClienteDTO cliente, BigDecimal preco, TipoDescontoPedidoEnum tipoDescontoPedido) {
        this.dataPedido = dataPedido;
        this.itens = itens;
        this.cliente = cliente;
        this.preco = preco;
        this.tipoDescontoPedido = tipoDescontoPedido;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public TipoDescontoPedidoEnum getTipoDescontoPedido() {
        return tipoDescontoPedido;
    }

    public void setTipoDescontoPedido(TipoDescontoPedidoEnum tipoDescontoPedido) {
        this.tipoDescontoPedido = tipoDescontoPedido;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;

    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}