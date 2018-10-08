package com.skysoft.vaultlogic.common.domain.application;

import com.skysoft.vaultlogic.common.domain.application.events.ApplicationCreated;
import com.skysoft.vaultlogic.common.domain.user.User;
import lombok.Getter;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
public class Application extends AbstractAggregateRoot<Application> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(updatable = false, insertable = false)
    private BigInteger id;

    @NaturalId
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String uri;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false, unique = true, length = 42)
    private String address;

    @Enumerated(STRING)
    @Column(nullable = false)
    private Status status;

    public Application() {
    }

    private Application(String name, String uri, User owner, String address) {
        this.name = name;
        this.uri = uri;
        this.owner = owner;
        this.address = address;
    }

    public static Application newApplication(String name, String uri, User owner, String address) {
        requireNonNull(name);
        requireNonNull(uri);
        requireNonNull(owner);
        requireNonNull(address);
        return new Application(name, uri, owner, address);
    }

    public Application publishCreated() {
        this.status = Status.UNCONFIRMED;
        return andEvent(ApplicationCreated.of(name));
    }

    public void setAddress(String address) {
        this.address = requireNonNull(address, "Address field can't be null");
    }

    public enum Status {

        UNCONFIRMED(BigInteger.ZERO), CONFIRMED(BigInteger.ONE), ENABLED(BigInteger.valueOf(2)), DISABLED(BigInteger.valueOf(3));

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

        private static Predicate<Status> equalTo(BigInteger value) {
            return status -> status.getValue().compareTo(value) == 0;
        }

    }

}