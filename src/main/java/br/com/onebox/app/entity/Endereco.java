package br.com.onebox.app.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

    @Column(name= "rua_clientes" ,nullable = false)
    private String rua;


    @Column(name= "numero_clientes" ,nullable = false)
    private String numero;

    @Column(name = "complemento_clientes")
    private String complemento;

    @Column(name ="bairro_clientes" , nullable = false)
    private String bairro;

    @Column(name = "cidade_clientes",nullable = false)
    private String cidade;

    @Column(name= "estado_clientes", nullable = false)
    private String estado;


    public Endereco(
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado){
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;

    }

    public Endereco() {
    }

    public Endereco(String endereco) {
        String[] partes = endereco.split(",");
        if (partes.length == 6) {
            this.rua = partes[0];
            this.numero = partes[1];
            this.complemento = partes[2];
            this.bairro = partes[3];
            this.cidade = partes[4];
            this.estado = partes[5];
        } else {
            // handle the case where the input string is not in the expected format
        }
    }

    public Endereco(Endereco endereco) {
    }


    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
