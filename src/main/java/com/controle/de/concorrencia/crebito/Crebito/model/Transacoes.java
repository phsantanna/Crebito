package com.controle.de.concorrencia.crebito.Crebito.model;


import com.controle.de.concorrencia.crebito.Crebito.enums.Tipo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "transacoes")
@Table(name = "tb_transacoes")
@Getter
@Setter
@NoArgsConstructor
public class Transacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double valor;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    private String descricao;
    private LocalDateTime realizada_em;
    @ManyToOne
    @JoinColumn(name = "cliente_id") // Nome da coluna que é chave estrangeira para cliente
    @JsonIgnore
    private Cliente cliente;

    public Transacoes(double valor, Tipo tipo, String descricao) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Transacoes that = (Transacoes) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
