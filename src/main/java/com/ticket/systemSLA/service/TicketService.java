package com.ticket.systemSLA.service;

import com.ticket.systemSLA.dto.TicketRequest;
import com.ticket.systemSLA.exception.TransicaoInvalida;
import com.ticket.systemSLA.model.*;
import com.ticket.systemSLA.repository.TicketRepository;
import com.ticket.systemSLA.exception.RecursoNaoEncontrado;
import org.springframework.stereotype.Service;


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
                new RecursoNaoEncontrado("Ticket " + id + " não encontrado"));
    }

    public List<Ticket> listar() {
        return repository.findAll();
    }

    public Ticket atualizarStatus(Long id, StatusTicket novoStatus) {
        Ticket ticket = buscarPorId(id);

        if (!ticket.getStatus().podeIrPara(novoStatus)) {
            throw new TransicaoInvalida (
                    "Não é possível mudar de " + ticket.getStatus() + " para " + novoStatus);
        }

        ticket.setStatus(novoStatus);
        if (novoStatus == StatusTicket.RESOLVIDO) {
            ticket.setResolvidoEm(LocalDateTime.now());
        }
        if (novoStatus == StatusTicket.EM_ANDAMENTO) {
            ticket.setResolvidoEm(null); // chamado reaberto
        }
        return repository.save(ticket);
    }
}