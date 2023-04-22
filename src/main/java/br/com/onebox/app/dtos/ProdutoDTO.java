package br.com.onebox.app.dtos;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ProdutoDTO {
    @NotBlank
    @Size(min = 2)
    private String nome;

    @NotNull
    @Positive
    private BigDecimal precoUnitario;

    private String descricao;

    @NotNull
    @PositiveOrZero
    private Integer quantidadeEstoque;

    @NotNull
    private Long idCategoria;

    @NotNull
    private String categoriaNome;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getDescricao() {
        return descricao;
    }
    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }
}
