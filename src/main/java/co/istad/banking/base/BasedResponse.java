package co.istad.banking.base;

import lombok.Builder;

@Builder
public class BasedResponse<T> {
    private T payload;
}
