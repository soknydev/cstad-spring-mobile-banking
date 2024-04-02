package co.istad.banking.features.cardtypes;

import co.istad.banking.domain.CardType;
import co.istad.banking.features.cardtypes.dto.CardTypeResponse;
import co.istad.banking.mapper.CardTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardTypeServiceImpl implements CardTypeService{

    private final CardTypeRepository cardTypeRepository;
    private final CardTypeMapper cardTypeMapper;

    @Override
    public List<CardTypeResponse> findList() {
        List<CardType> cardTypeList = cardTypeRepository.findAll();
        return cardTypeMapper.toCardTypeResponseList(cardTypeList);
    }

    @Override
    public CardTypeResponse findByName(String name) {
        CardType cardType = cardTypeRepository.findByName(name)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Not found"
                        ));
        return cardTypeMapper.toCardTypeResponse(cardType);
    }
}
