package com.skysoft.vaultlogic.common.domain;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@MappedSuperclass
public abstract class TransactionLog<E, S> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(insertable = false, updatable = false)
    protected BigInteger id;

    @NaturalId
    @Column(nullable = false, unique = true)
    protected String transactionHash;

    @ManyToOne(fetch = LAZY, optional = false)
    protected E entity;

    @Enumerated(STRING)
    @Column(nullable = false)
    protected S action;

    public TransactionLog() {
    }

    public TransactionLog(String transactionHash, E entity, S action) {
        this.transactionHash = Objects.requireNonNull(transactionHash);
        this.entity = Objects.requireNonNull(entity);
        this.action = Objects.requireNonNull(action);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionLog<?, ?> that = (TransactionLog<?, ?>) o;
        return Objects.equals(transactionHash, that.transactionHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionHash);
    }

}
