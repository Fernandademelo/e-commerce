package br.com.onebox.app.entity;

import br.com.onebox.app.dtos.ClienteDTO;
import br.com.onebox.app.enums.TipoDescontoPedidoEnum;
import br.com.onebox.app.users.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "primeiro_nome_clientes", nullable = false)
    private String primeiroNome;

    @Column(name = "sobrenome_clientes", nullable = false)
    private String sobrenome;

    @Column(name = "cpf_clientes", nullable = false, length = 11)
    private String cpf = "00000000000"; // Default value

    @Column(name = "telefone_clientes", nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Embedded
    public Endereco endereco;
    public Cliente(String nome, String cpf, Endereco endereco) {
        this.primeiroNome = nome;
        this.cpf = cpf;
        this.endereco = new Endereco(endereco);
    }

    public Cliente(ClienteDTO clienteDTO) {
        this.primeiroNome = clienteDTO.getPrimeiroNome();
        this.sobrenome = clienteDTO.getSobrenome();
        this.cpf = clienteDTO.getCpf();
        this.telefone = clienteDTO.getTelefone();
        this.endereco = clienteDTO.getEndereco();
        this.id = clienteDTO.getId();
    }

    public Cliente(String primeiroNome) {
        this.primeiroNome = primeiroNome ;
    }

    public Cliente() {

    }

    @Override
    public String toString() {
        return "Cliente{" +
                "\n" + "Id:" + id +
                "\n" + " PrimeiroNome='" + primeiroNome + '\'' +
                "\n" + " Sobrenome:'" + sobrenome + '\'' +
                "\n" + " Cpf:'" + cpf + '\'' +
                "\n" + " Telefone:'" + telefone + '\'' +
                "\n" + " Endereço:" + endereco.toString();
    }

    public Long getId() {
        return id;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    public List<Pedido> getPedidos() {
        return this.pedidos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    public TipoDescontoPedidoEnum getTipoDescontoPedido() {
        if (this.getPedidos().size() >= 5) {
            return TipoDescontoPedidoEnum.FIDELIDADE;
        } else {
            return TipoDescontoPedidoEnum.NENHUM;
        }
    }

}
