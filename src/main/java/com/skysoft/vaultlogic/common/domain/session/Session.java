package com.skysoft.vaultlogic.common.domain.session;

import com.skysoft.vaultlogic.common.domain.application.Application;
import com.skysoft.vaultlogic.common.domain.session.events.*;
import lombok.Getter;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;

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
    private Long id;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @NaturalId
    @Column(nullable = false, unique = true, length = 36)
    private String xToken;

    @Enumerated(STRING)
    @Column(nullable = false)
    private Status status;

    public Session() {
    }

    private Session(Application application, String xToken) {
        this.application = application;
        this.xToken = xToken;
    }

    public static Session newApplicationSession(Application application, String xToken) {
        return new Session(requireNonNull(application), requireNonNull(xToken));
    }

    public Session markCreating() {
        this.status = Status.CREATING;
        return andEvent(SessionCreating.of(this.xToken));
    }

    public Session markActive() {
        this.status = Status.ACTIVE;
        return andEvent(SessionActivated.of(this.xToken));
    }

    public Session markCreateFail() {
        this.status = Status.FAILED_TO_CREATE;
        return andEvent(SessionCreateFail.of(this.xToken));
    }

    public Session markCloseRequested() {
        this.status = Status.CLOSE_REQUESTED;
        return andEvent(SessionCloseRequested.of(this.xToken));
    }

    public Session markClosed() {
        this.status = Status.CLOSED;
        return andEvent(SessionClosed.of(this.xToken));
    }

    public Session markCloseFail() {
        this.status = Status.FAILED_TO_CLOSE;
        return andEvent(SessionCloseFail.of(this.xToken));
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

        CREATING(1), ACTIVE(2), FAILED_TO_CREATE(3), CLOSE_REQUESTED(4), CLOSED(5), FAILED_TO_CLOSE(6);

        private final int value;

        Status(int value) {
            this.value = value;
        }

        public static Status from(int value) {
            return Stream.of(values()).filter(equalTo(value)).findAny().orElseThrow(RuntimeException::new);
        }

        private static Predicate<Session.Status> equalTo(int value) {
            return status -> status.value == value;
        }

    }

}
