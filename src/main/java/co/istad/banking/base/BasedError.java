package co.istad.banking.base;


import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
public class BasedError<T> {
    // request Entity Too large , Bad Request...
    // 7003
    private String code;
    // detail error for user
    private T description;
}
