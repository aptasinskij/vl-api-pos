package com.skysoft.vaultlogic.common.domain.cashin;

import com.skysoft.vaultlogic.common.domain.cashin.events.*;
import com.skysoft.vaultlogic.common.domain.session.Session;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
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

@Getter
@Entity
@DynamicUpdate
@SelectBeforeUpdate(value = false)
public class CashInChannel extends AbstractAggregateRoot<CashInChannel> {

    @Id
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

    public CashInChannel markCreating() {
        this.status = Status.CREATING;
        return andEvent(CashInCreating.of(this.channelId));
    }

    public CashInChannel markActive() {
        this.status = Status.ACTIVE;
        return andEvent(CashInActivated.of(this.channelId));
    }

    public CashInChannel markFailedToCreate() {
        this.status = Status.FAILED_TO_CREATE;
        return andEvent(CashInFailedToCreate.of(this.channelId));
    }

    public CashInChannel markCloseRequested() {
        this.status = Status.CLOSE_REQUESTED;
        return andEvent(CashInCloseRequested.of(this.channelId));
    }

    public CashInChannel markClosed() {
        this.status = Status.CLOSED;
        return andEvent(CashInClosed.of(this.channelId));
    }

    public CashInChannel markFailedToClose() {
        this.status = Status.FAILED_TO_CLOSE;
        return andEvent(CashInFailedToClose.of(this.channelId));
    }

    public <T extends Number> CashInChannel updateBalance(T amount) {
        this.balance = this.balance.add(BigInteger.valueOf(amount.longValue()));
        return andEvent(CashInBalanceUpdate.of(this.channelId, BigInteger.valueOf(amount.longValue())));
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

        CREATING(BigInteger.valueOf(0)),
        ACTIVE(BigInteger.valueOf(1)),
        FAILED_TO_CREATE(BigInteger.valueOf(2)),
        CLOSE_REQUESTED(BigInteger.valueOf(3)),
        CLOSED(BigInteger.valueOf(4)),
        FAILED_TO_CLOSE(BigInteger.valueOf(5));

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
