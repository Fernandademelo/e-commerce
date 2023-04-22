package br.com.onebox.app.entity;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_categorias", nullable = false)
    @Size(min = 2, message = "O nome deve possuir pelo menos 2 caracteres.")
    private String nome;

    @Column(name = "status_categorias", nullable = false)
    private Boolean status;

    public Categoria() {
    }

    public Categoria(String nome, Boolean status) {
        this.nome = nome;
        this.status = status;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return nome + " (" + id + " - " + (status ? "ATIVO" : "INATIVO") + ")";
    }
}
