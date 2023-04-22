package br.com.onebox.app.controllers;


import br.com.onebox.app.entity.Pedido;
import br.com.onebox.app.service.PedidoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.onebox.app.dtos.PedidoDTO;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/criarpedido")
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.criarPedido(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }


    @GetMapping("/testarpedido")
    public ResponseEntity<String> testarPedido() {
        return ResponseEntity.ok("Endpoint para testar pedidos funcionando!");
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }
}
