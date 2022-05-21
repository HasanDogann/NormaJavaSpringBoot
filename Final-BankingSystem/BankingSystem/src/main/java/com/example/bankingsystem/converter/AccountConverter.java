package com.example.bankingsystem.converter;

import com.example.bankingsystem.model.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.AccountGetResponseDTO;
import com.example.bankingsystem.model.entity.Account;

public interface AccountConverter {

    Account convertToAccount(AccountCreateRequestDTO accountCreateRequestDTO);

    AccountGetResponseDTO convertAccountToResponseDto(Account account);
}
