package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.skysoft.vaultlogic.clients.api.model.Cassette.Type.cash;
import static java.util.stream.Collectors.toList;

@Data
@JsonInclude(NON_NULL)
public class Cassette {

    private String id;

    private BigInteger count;

    private BigDecimal denomination;

    private BigInteger amount;

    public Cassette(BigDecimal denomination, Type type) {
        this.denomination = denomination;
        this.type = type;
    }

    @JsonProperty("max_count")
    private BigDecimal maxCount;

    private Type type;

    public enum Type {
        cash, coin
    }

    public static boolean isCash(Cassette cassette) {
        return cash.equals(cassette.getType());
    }

    public static Pair<BigInteger, BigInteger> denominationToCountPair(Cassette cassette) {
        return Pair.of(cassette.getDenomination().toBigInteger(), cassette.getCount());
    }

    public static List<Cassette> fromBillsForDispense(List<BigInteger> bills) {
        return bills.stream()
                .map(bigInteger -> new Cassette(new BigDecimal(bigInteger), cash))
                .collect(toList());
    }

}
