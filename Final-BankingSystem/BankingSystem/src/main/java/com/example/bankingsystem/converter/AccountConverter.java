package com.example.bankingsystem.converter;

import com.example.bankingsystem.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.dto.response.AccountGetResponseDTO;
import com.example.bankingsystem.entity.Account;

public interface AccountConverter {

    Account convertToAccount(AccountCreateRequestDTO accountCreateRequestDTO);

    AccountGetResponseDTO convertAccountToResponseDto(Account account);
}
