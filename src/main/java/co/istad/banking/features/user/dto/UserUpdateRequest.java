package co.istad.banking.features.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UserUpdateRequest(

        @Column(length = 50)
        String name,

        @NotNull
        LocalDate dob,

        String gender,
        String profileImage,

        @Size(max = 20)
        String studentIdCard

        /*@Column(length = 100)
        String cityOrProvince,

        @Column(length = 100)
        String khanOrDistrict,

        @Column(length = 100)
        String sangkatOrCommune,

        @Column(length = 100)
        String village,

        @Column(length = 100)
        String street,

        @Column(length = 100)
        String employeeType,

        @Column(length = 100)
        String position,

        @Column(length = 100)
        String companyName,

        @Column(length = 100)
        String mainSourceOfIncome,
        BigDecimal monthlyIncomeRange*/
) {
}
