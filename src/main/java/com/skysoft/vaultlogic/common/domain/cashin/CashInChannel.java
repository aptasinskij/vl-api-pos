package com.skysoft.vaultlogic.common.domain.cashin;

import com.skysoft.vaultlogic.common.domain.session.Session;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity
public class CashInChannel {

    @Id
    private Long id;

    @JoinColumn(name = "session_id")
    @ManyToOne(fetch = LAZY, optional = false)
    private Session session;

    @Column(nullable = false)
    private BigInteger balance = BigInteger.ZERO;

    @ElementCollection
    /*@CollectionTable("")*/
    private Set<Split> splits = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashInChannel that = (CashInChannel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
