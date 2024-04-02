package co.istad.banking.mapper;

import co.istad.banking.domain.CardType;
import co.istad.banking.features.cardtypes.dto.CardTypeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardTypeMapper {

    CardTypeResponse toCardTypeResponse(CardType cardType);

    List<CardTypeResponse> toCardTypeResponseList(List<CardType> cardTypeList);
}
