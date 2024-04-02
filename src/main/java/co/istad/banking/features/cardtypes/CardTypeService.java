package co.istad.banking.features.cardtypes;

import co.istad.banking.features.cardtypes.dto.CardTypeResponse;

import java.util.List;

public interface CardTypeService {

    List<CardTypeResponse> findList();

    CardTypeResponse findByName(String name);
}
