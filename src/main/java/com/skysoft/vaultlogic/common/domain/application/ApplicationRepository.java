package com.skysoft.vaultlogic.common.domain.application;

import com.skysoft.vaultlogic.common.domain.application.projections.AppUriStatus;
import com.skysoft.vaultlogic.common.domain.application.projections.SmartContractApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByName(String name);

    SmartContractApplication findSmartContractApplicationByName(String name);

    Optional<AppUriStatus> findAppUriStatusById(Long id);

    @Query("select a.uri from Application a where a.name = :name")
    Optional<String> selectApplicationUrlByName(@Param("name") String name);

    Application getByName(String name);

}
