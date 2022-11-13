package ru.hackandchallenge.investadvisor.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackandchallenge.investadvisor.entity.Asset;
import ru.hackandchallenge.investadvisor.dto.NotificationDto;
import ru.hackandchallenge.investadvisor.repository.AssetsRepository;
import ru.hackandchallenge.investadvisor.repository.NotificationRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationsService {

    private final NotificationRepository notificationRepository;
    private final AssetsRepository assetsRepository;


    @Transactional
    public List<NotificationDto> getAllByClientId(Long clientId) {
        List<NotificationDto> notificationDtos = notificationRepository.findAllByClientId(clientId)
                .stream()
                .map(entity -> {
                    Asset asset = assetsRepository.findById(entity.getAssetId())
                            .orElseThrow(EntityNotFoundException::new);
                    return new NotificationDto(entity.getId(), entity.getClientId(), entity.getType(),
                            asset.getFullName(), entity.getOldCost(), entity.getActualCost(), entity.getTime());
                })
                .toList();
        this.deleteAllByClientId(clientId);

        return notificationDtos;
    }

    public void deleteAllByClientId(Long clientId) {
        notificationRepository.deleteAllByClientId(clientId);
    }
}
