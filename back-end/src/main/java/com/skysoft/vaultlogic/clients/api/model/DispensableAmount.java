package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Predicate;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.math.BigDecimal.valueOf;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class DispensableAmount extends StatusCode {

    @JsonAlias("available_amount")
    @JsonProperty("requested_amount")
    private BigDecimal amount;

    @JsonInclude(value = NON_NULL, content = NON_EMPTY)
    private List<Cassette> cassettes;

    public DispensableAmount() {
    }

    public DispensableAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public DispensableAmount(BigDecimal amount, List<Cassette> cassettes) {
        this.amount = amount;
        this.cassettes = cassettes;
    }

    public static DispensableAmount from(BigInteger amount, List<Cassette> cassettes) {
        return new DispensableAmount(new BigDecimal(amount), cassettes);
    }

    public static DispensableAmount max() {
        return new DispensableAmount(valueOf(9999999));
    }

    public static Predicate<DispensableAmount> greaterOrEqualTo(BigInteger toWithdraw) {
        return amount -> amount.getAmount().compareTo(new BigDecimal(toWithdraw)) >= 0;
    }

}