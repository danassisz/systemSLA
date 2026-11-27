package com.ticket.systemSLA.model;

public enum PrioridadeTicket {

    BAIXA(72), MEDIA(24), ALTA(8), CRITICA(4);

    private final int horasSla;

    PrioridadeTicket(int horasSla) {
        this.horasSla = horasSla;
    }
    public int getHorasSla() {
        return horasSla;
    }
}
