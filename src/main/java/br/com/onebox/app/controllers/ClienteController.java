package br.com.onebox.app.controllers;

import br.com.onebox.app.dtos.ClienteDTO;
import br.com.onebox.app.entity.Cliente;
import br.com.onebox.app.entity.Pedido;
import br.com.onebox.app.exceptions.CPFInvalidoException;
import br.com.onebox.app.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {
        clienteService.cadastrar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }


    @GetMapping("/todos")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }
    @GetMapping("/{id}")
    public ClienteDTO getById(@PathVariable Long id) throws Exception {
        return clienteService.getById(id);
    }

}