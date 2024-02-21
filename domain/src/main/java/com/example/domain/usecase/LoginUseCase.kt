package com.example.domain.usecase


import com.example.domain.entity.LoginRequest
import com.example.domain.entity.LoginResponse

import com.example.domain.abstraction.Repo.Repo

class LoginUseCase(val repo: Repo) {
    suspend operator fun invoke(request: LoginRequest): LoginResponse {
        return repo.login(request)
    }
}