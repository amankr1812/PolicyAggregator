package com.aggregator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aggregator.entity.ProvidersList;

@Repository
public interface ProvidersListRepository extends JpaRepository<ProvidersList, Integer> {}
