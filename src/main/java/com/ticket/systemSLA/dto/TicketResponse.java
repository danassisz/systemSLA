package com.ticket.systemSLA.dto;

import com.ticket.systemSLA.model.*;
import java.time.LocalDateTime;

public record TicketResponse (
        Long id,
        String titulo,
        String descricao,
        StatusTicket status,
        PrioridadeTicket prioridade,
        String solicitante,
        LocalDateTime criadoEm,
        LocalDateTime prazoSla
) {
    public static TicketResponse from(Ticket t) {
        return new TicketResponse(t.getId(), t.getTitulo(), t.getDescricao(),
                t.getStatus(), t.getPrioridade(), t.getSolicitante(),
                t.getCriadoEm(), t.getPrazoSla());
    }
}