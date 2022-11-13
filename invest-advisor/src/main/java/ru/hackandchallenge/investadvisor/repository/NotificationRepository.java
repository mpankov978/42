package ru.hackandchallenge.investadvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import ru.hackandchallenge.investadvisor.entity.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByClientId(Long clientId);

    @Modifying
    void deleteAllByClientId(Long clientId);
}