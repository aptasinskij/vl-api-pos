package com.skysoft.vaultlogic.common.domain.kiosk;

import lombok.Getter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Immutable
public class Kiosk {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(insertable = false, updatable = false)
    private BigInteger id;

    @NaturalId
    @Column(nullable = false, unique = true)
    private String shortId;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String timeZone;

    public Kiosk() {
    }

    private Kiosk(String shortId, String address, String name, String timeZone) {
        this.shortId = shortId;
        this.address = address;
        this.name = name;
        this.timeZone = timeZone;
    }

    public static Kiosk kiosk(String shortId, String address, String name, String timeZone) {
        requireNonNull(shortId, "short id can not be null");
        requireNonNull(address, "address can not be null");
        requireNonNull(name, "name can not be null");
        requireNonNull(timeZone, "time zone can not be null");
        return new Kiosk(shortId, address, name, timeZone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kiosk kiosk = (Kiosk) o;
        return Objects.equals(shortId, kiosk.shortId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortId);
    }

}
