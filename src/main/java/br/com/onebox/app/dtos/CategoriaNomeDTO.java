package br.com.onebox.app.dtos;
import javax.validation.constraints.*;

public class CategoriaNomeDTO {
    @NotBlank(message = "O nome da categoria é obrigatório")
    @Size(min = 2, message = "O nome da categoria deve ter pelo menos 2 caracteres")
    private String nome;
    private Boolean status = true; // valor padrão é true (ATIVO)

    public CategoriaNomeDTO() {
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

    public CategoriaNomeDTO(String nome, Boolean status) {
        this.nome = nome;
        this.status = status;
    }
}
