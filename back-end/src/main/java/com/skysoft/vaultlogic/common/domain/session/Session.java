package com.skysoft.vaultlogic.common.domain.session;

import com.skysoft.vaultlogic.common.domain.application.Application;
import com.skysoft.vaultlogic.common.domain.cashin.CashInChannel;
import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;
import com.skysoft.vaultlogic.common.domain.session.events.*;
import lombok.Getter;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
public class Session extends AbstractAggregateRoot<Session> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(updatable = false, insertable = false)
    private BigInteger id;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @NaturalId
    @Column(nullable = false, unique = true, length = 36)
    private String xToken;

    @Enumerated(STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "session", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CashInChannel> cashInChannels = new ArrayList<>();

    @JoinColumn(name = "kiosk_id")
    @ManyToOne(fetch = LAZY, optional = false)
    private Kiosk kiosk;

    public Session() {
    }

    private Session(Application application, Kiosk kiosk, String xToken) {
        this.application = application;
        this.kiosk = kiosk;
        this.xToken = xToken;
    }

    public static Session session(Application application, Kiosk kiosk, String xToken) {
        return new Session(requireNonNull(application), requireNonNull(kiosk), requireNonNull(xToken));
    }

    public Session markCreating() {
        this.status = Status.CREATING;
        return andEvent(SessionCreating.of(this.xToken));
    }

    public Session markActive() {
        this.status = Status.ACTIVE;
        return andEvent(SessionActivated.of(this.xToken));
    }

    public Session markFailedToCreate() {
        this.status = Status.FAILED_TO_CREATE;
        return andEvent(SessionFailedToCreate.of(this.xToken));
    }

    public Session markCloseRequested() {
        this.status = Status.CLOSE_REQUESTED;
        return andEvent(SessionCloseRequested.of(this.xToken));
    }

    public Session markClosed() {
        this.status = Status.CLOSED;
        return andEvent(SessionClosed.of(this.xToken));
    }

    public Session markFailedToClose() {
        this.status = Status.FAILED_TO_CLOSE;
        return andEvent(SessionFailedToClose.of(this.xToken));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(xToken, session.xToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xToken);
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

        public static Status from(BigInteger value) {
            return Stream.of(values()).filter(equalTo(value)).findAny().orElseThrow(RuntimeException::new);
        }

        private static Predicate<Session.Status> equalTo(BigInteger value) {
            return status -> status.value.equals(value);
        }

    }

}
