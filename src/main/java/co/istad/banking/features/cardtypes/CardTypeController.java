package co.istad.banking.features.cardtypes;

import co.istad.banking.features.cardtypes.dto.CardTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/card-type/")
public class CardTypeController {

    private final CardTypeService cardTypeService;

    @GetMapping
    List<CardTypeResponse> findList(){
        return cardTypeService.findList();
    }

    @GetMapping("/{name}")
    CardTypeResponse findByName(@PathVariable String name){
        return cardTypeService.findByName(name);
    }
}
