package com.ticket.systemSLA.dto;

import com.ticket.systemSLA.model.StatusTicket;
import jakarta.validation.constraints.NotNull;

public record AtualizarStatusRequest(@NotNull StatusTicket status) {}