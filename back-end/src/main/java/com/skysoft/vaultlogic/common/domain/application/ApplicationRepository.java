package com.skysoft.vaultlogic.common.domain.application;

import com.skysoft.vaultlogic.common.domain.application.projections.AppUriStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, BigInteger> {

    Optional<Application> findByName(String name);

    @Query("select a from Application a join a.owner where a.name = :name")
    Optional<Application> findByNameJoinOwner(@Param("name") String name);

    Optional<AppUriStatus> findAppUriStatusById(BigInteger id);

    Application getByName(String name);

}
