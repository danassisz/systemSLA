package com.ticket.systemSLA.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity

@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 2000)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTicket status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrioridadeTicket prioridade;

    @Column(nullable = false)
    private String solicitante;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @Column(nullable = false)
    private LocalDateTime prazoSla;

    private LocalDateTime resolvidoEm;

}