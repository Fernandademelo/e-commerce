package br.com.onebox.app.service;

import br.com.onebox.app.dtos.ClienteDTO;
import br.com.onebox.app.entity.Cliente;
import br.com.onebox.app.exceptions.CPFInvalidoException;
import br.com.onebox.app.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private List<Cliente> clientes = new ArrayList<>();

    public void cadastrar(Cliente novoCliente) throws CPFInvalidoException {
        if (!validarCPF(novoCliente.getCpf())) {
            throw new CPFInvalidoException("CPF INVALIDO!");
        }
        if (novoCliente.getPedidos() == null) {
            int quantidadePedidos = 0;
        }
        clienteRepository.save(novoCliente);
    }


    private boolean validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return false;
        }
        return true;
    }

    public Optional<Cliente> get(String cpf) throws CPFInvalidoException {
        if (!validarCPF(cpf)) {
            throw new CPFInvalidoException("CPF INVÁLIDO");
        }
        return clientes.stream()
                .filter(c -> cpf.equals(c.getCpf()))
                .findFirst();


    }

    public ClienteDTO getById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setPrimeiroNome(cliente.getPrimeiroNome());
        clienteDTO.setSobrenome(cliente.getSobrenome());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setTelefone(cliente.getTelefone());
        clienteDTO.setEndereco(cliente.getEndereco());

        return clienteDTO;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
}
