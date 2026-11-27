package com.ticket.systemSLA.service;

import com.ticket.systemSLA.dto.TicketRequest;
import com.ticket.systemSLA.model.*;
import com.ticket.systemSLA.repository.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public Ticket criar(TicketRequest request) {
        LocalDateTime agora = LocalDateTime.now();

        Ticket ticket = new Ticket();
        ticket.setTitulo(request.titulo());
        ticket.setDescricao(request.descricao());
        ticket.setSolicitante(request.solicitante());
        ticket.setPrioridade(request.prioridade());
        ticket.setStatus(StatusTicket.ABERTO);
        ticket.setCriadoEm(agora);
        ticket.setPrazoSla(agora.plusHours(request.prioridade().getHorasSla()));

        return repository.save(ticket);
    }

    public Ticket buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket não encontrado"));
    }

    public List<Ticket> listar() {
        return repository.findAll();
    }
}