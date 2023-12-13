package niu.itmo.spaceresearch.service.api;

import niu.itmo.spaceresearch.model.Expedition;

import java.util.List;

/**
 * @author amifideles
 */
public interface ExpeditionService {
    List<Expedition> getExpeditionsByUsername(String username);
}
