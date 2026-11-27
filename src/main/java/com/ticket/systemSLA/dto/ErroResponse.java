package com.ticket.systemSLA.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErroResponse (
        int status,
        String mensagem,
        LocalDateTime timestamp,
        Map<String, String> campos
) {}