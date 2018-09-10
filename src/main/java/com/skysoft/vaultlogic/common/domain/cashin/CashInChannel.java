package com.skysoft.vaultlogic.common.domain.cashin;

import com.skysoft.vaultlogic.common.domain.cashin.events.*;
import com.skysoft.vaultlogic.common.domain.session.Session;
import lombok.Getter;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
public class CashInChannel extends AbstractAggregateRoot<CashInChannel> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NaturalId
    @Column(nullable = false)
    private BigInteger channelId;

    @JoinColumn(name = "session_id")
    @ManyToOne(fetch = LAZY, optional = false)
    private Session session;

    @Column(nullable = false)
    private BigInteger balance = BigInteger.ZERO;

    @ElementCollection
    private Set<Split> splits = new HashSet<>();

    @Enumerated(STRING)
    @Column(nullable = false)
    private Status status;

    public CashInChannel() {
    }

    private CashInChannel(BigInteger channelId, Session session, Status status) {
        this.channelId = channelId;
        this.session = session;
        this.status = status;
    }

    public static CashInChannel newChannel(BigInteger channelId, Session session, Status status) {
        requireNonNull(channelId);
        requireNonNull(session);
        requireNonNull(status);
        return new CashInChannel(channelId, session, status);
    }

    public CashInChannel emitCreated() {
        registerEvent(CashInCreated.of(this.session.getXToken(), this.channelId));
        return this;
    }

    public CashInChannel markOpened() {
        this.status = Status.OPENED;
        return andEvent(CashInOpened.of(this.channelId));
    }

    public CashInChannel markHalfClosed(String xToken) {
        this.status = Status.HALF_CLOSED;
        return andEvent(CashInCloseRequested.of(this.channelId, xToken));
    }

    public CashInChannel markClosed() {
        this.status = Status.CLOSED;
        return andEvent(CashInClosed.of(this.channelId));
    }

    public CashInChannel updateBalance(BigInteger amount) {
        this.balance = this.balance.add(amount);
        return andEvent(CashInBalanceUpdate.of(this.channelId, amount));
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashInChannel that = (CashInChannel) o;
        return Objects.equals(channelId, that.channelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channelId);
    }

    public enum Status {

        PENDING(BigInteger.valueOf(0)),
        OPENED(BigInteger.valueOf(1)),
        HALF_CLOSED(BigInteger.valueOf(2)),
        CLOSED(BigInteger.valueOf(3));

        private final BigInteger value;

        Status(BigInteger value) {
            this.value = value;
        }

        public BigInteger getValue() {
            return value;
        }

        public static Status from(BigInteger value) {
            return Stream.of(values()).filter(equalTo(value)).findAny().orElseThrow(RuntimeException::new);
        }

        private static Predicate<CashInChannel.Status> equalTo(BigInteger value) {
            return status -> status.getValue().compareTo(value) == 0;
        }

    }

}
