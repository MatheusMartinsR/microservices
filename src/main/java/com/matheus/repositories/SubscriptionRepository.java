package com.matheus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.matheus.domain.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}
