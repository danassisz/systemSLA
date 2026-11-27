package com.ticket.systemSLA.controller;

import com.ticket.systemSLA.dto.*;
import com.ticket.systemSLA.model.Ticket;
import com.ticket.systemSLA.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tickets")

public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TicketResponse> criar(@Valid @RequestBody TicketRequest request) {
        Ticket ticket = service.criar(request);
        return ResponseEntity
                .created(URI.create("/tickets/" + ticket.getId()))
                .body(TicketResponse.from(ticket));
    }

    @GetMapping("/{id}")
    public TicketResponse buscar(@PathVariable Long id) {
        return TicketResponse.from(service.buscarPorId(id));
    }

    @GetMapping
    public List<TicketResponse> listar() {
        return service.listar().stream().map(TicketResponse::from).toList();
    }

    @PatchMapping("/{id}/status")
    public TicketResponse atualizarStatus(@PathVariable Long id,
                                          @Valid @RequestBody AtualizarStatusRequest request) {
        return TicketResponse.from(service.atualizarStatus(id, request.status()));
    }
}