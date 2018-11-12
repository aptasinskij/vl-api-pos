package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class Cassette {

    private String id;

    private BigInteger count;

    private BigDecimal denomination;

    private BigInteger amount;

    @JsonProperty("max_count")
    private BigDecimal maxCount;

    private Type type;

    public enum Type {
        cash, coin
    }

    public static boolean isCash(Cassette cassette) {
        return Type.cash.equals(cassette.getType());
    }

    public static Pair<BigInteger, BigInteger> denominationToCountPair(Cassette cassette) {
        return Pair.of(cassette.getDenomination().toBigInteger(), cassette.getCount());
    }

}
