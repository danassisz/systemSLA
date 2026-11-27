package com.ticket.systemSLA.dto;

import com.ticket.systemSLA.model.PrioridadeTicket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TicketRequest(
        @NotBlank String titulo,
        String descricao,
        @NotBlank String solicitante,
        @NotNull PrioridadeTicket prioridade
) {}