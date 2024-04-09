package co.istad.banking.features.transaction;

import co.istad.banking.features.transaction.dto.TransactionCreateRequest;
import co.istad.banking.features.transaction.dto.TransactionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions/")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    TransactionResponse transfer(@Valid @RequestBody TransactionCreateRequest request){
        return transactionService.transfer(request);
    }

    @GetMapping
    Page<TransactionResponse> findTransaction(@RequestParam(required = false, defaultValue = "0") int page,
                                              @RequestParam(required = false, defaultValue = "10") int size,
                                              @RequestParam(required = false, defaultValue = "asc") String sort){
        return transactionService.findList(page, size, sort);
    }

}
