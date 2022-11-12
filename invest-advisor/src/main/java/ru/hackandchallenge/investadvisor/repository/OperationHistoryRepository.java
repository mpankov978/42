package ru.hackandchallenge.investadvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hackandchallenge.investadvisor.entity.OperationHistory;

import java.util.List;

@Repository
public interface OperationHistoryRepository extends JpaRepository<OperationHistory, Long> {
    List<OperationHistory> findOperationHistoriesByClientId(Long clientId);

    @Query(nativeQuery = true, value = "select * from public.operation_history e where (e.client_id = :clientId and e.operation_time >= NOW() - (make_interval(days => :days)))")
    List<OperationHistory> findOperationHistoriesByClientIdAndBetweenDays(Long clientId, Integer days);
}