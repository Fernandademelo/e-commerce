package br.com.onebox.app.entity;

import br.com.onebox.app.exceptions.PrecoInvalidoException;
import javax.validation.constraints.*;

import javax.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Table(name = "produtos")
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    @Size(min = 2, message = "O nome deve possuir pelo menos 2 caracteres.")
    private String nome;

    @Column(length = 240)
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "O preço é obrigatório.")
    @DecimalMin(value = "0.01", message = "O preço deve ser positivo.")
    private BigDecimal precoUnitario = BigDecimal.ZERO;

    @Column(nullable = false)
    @Max(value = 1000)
    @PositiveOrZero
    private int quantidadeEstoque;

    @ManyToOne
    private Categoria categoria;

    public Produto(Long id, String nome, String descricao, BigDecimal precoUnitario, int quantidadeEstoque, Categoria categoria) throws PrecoInvalidoException {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        setPrecoUnitario(precoUnitario);
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
    }

    public Produto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) throws PrecoInvalidoException {
        if (precoUnitario == null || precoUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PrecoInvalidoException("Preço inválido! O Preço deve ser maior que zero.");
        }
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal calculaValorTotalEstoque() {
        return this.precoUnitario.multiply(new BigDecimal(this.quantidadeEstoque).setScale(2, RoundingMode.HALF_UP));
    }

    public BigDecimal calculaImposto(BigDecimal precoUnitario) {
        return precoUnitario.multiply(BigDecimal.valueOf(0.4)).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", precoUnitario=" + precoUnitario +
                ", quantidadeEstoque=" + quantidadeEstoque +
                ", categoria=" + categoria +
                '}';
    }
}