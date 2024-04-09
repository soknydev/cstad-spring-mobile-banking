package co.istad.banking.features.transaction;


import co.istad.banking.domain.Account;
import co.istad.banking.domain.Transaction;
import co.istad.banking.features.account.AccountRepository;
import co.istad.banking.features.account.dto.AccountResponse;
import co.istad.banking.features.transaction.dto.TransactionCreateRequest;
import co.istad.banking.features.transaction.dto.TransactionResponse;
import co.istad.banking.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public TransactionResponse transfer(TransactionCreateRequest createRequest) {
        log.info("transfer(TransactionCreateRequest createRequest)");

        // validate owner account no
        Account owner = accountRepository.findByAccountNo(createRequest.ownerActNo())
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Account owner has been not found...!"
                        ));

        // validate transfer receive
        Account transferReceive = accountRepository.findByAccountNo(createRequest.transactionReceiveActNo())
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Account owner has been not found...!"
                        ));

        // check amount transfer ( amount <= balance ) for act owner only
        if(owner.getBalance().doubleValue() < createRequest.amount().doubleValue()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Insufficient balance"
            );
        }
        if(createRequest.amount().doubleValue() >= owner.getBalance().doubleValue()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Transaction has been over the transfer limit"
            );
        }

        // ដកលុយចេញពីគណនី
        owner.setBalance(owner.getBalance().subtract(createRequest.amount()));

        // បញ្ចូលលុយទៅគណនី
        transferReceive.setBalance(transferReceive.getBalance().add(createRequest.amount()));

        Transaction transaction = transactionMapper.fromTransactionCreateRequest(createRequest);
        transaction.setOwner(owner);
        transaction.setTransferReceiver(transferReceive);
        transaction.setTransactionType("TRANSFER");
        transaction.setTransactionAt(LocalDateTime.now());
        transaction.setStatus(true);
        transaction = transactionRepository.save(transaction);

        return transactionMapper.toTransactionResponse(transaction);

    }

    @Override
    public List<TransactionResponse> findTransaction() {
       List<Transaction> transactionList = transactionRepository.findAll();
        return transactionList.stream()
                .sorted(Comparator.comparing(Transaction::getTransactionAt).reversed())// Sorting by date
                .map(transactionMapper::toTransactionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<TransactionResponse> findList(int page, int size, String sort){
        // validate page and size
        if(page < 0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page number must be grater than 0");
        }
        if(size < 1){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "size number must be grater than or equal 1");
        }

        Sort sort1;
        if(sort.equals("DSC")){
            sort1 = Sort.by(Sort.Direction.DESC,"transactionAt");
        }
        else {
            sort1 = Sort.by(Sort.Direction.ASC,"transactionAt");
        }

        Sort sortByTransactionAt = sort1;
        //Sort sortByTransactionAt = Sort.by(Sort.Direction.ASC, "transactionAt");
        PageRequest pageRequest = PageRequest.of(page, size, sortByTransactionAt);
        Page<Transaction> transactions = transactionRepository.findAll(pageRequest);
        return transactions.map(transactionMapper::toTransactionResponse);
    }



}
