package com.ticket.systemSLA.model;

public enum StatusTicket {
    ABERTO, EM_ANDAMENTO, RESOLVIDO, FECHADO;

    public boolean podeIrPara(StatusTicket destino) {
        return switch (this) {
            case ABERTO -> destino == EM_ANDAMENTO;
            case EM_ANDAMENTO -> destino == RESOLVIDO;
            case RESOLVIDO -> destino == FECHADO || destino == EM_ANDAMENTO; // reabrir
            case FECHADO -> false;
        };
    }
}