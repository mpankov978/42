package ru.hackandchallenge.investadvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hackandchallenge.investadvisor.entity.Asset;

@Repository
public interface AssetsRepository extends JpaRepository<Asset, Long> {

    Asset findAssetByCode(String code);
}