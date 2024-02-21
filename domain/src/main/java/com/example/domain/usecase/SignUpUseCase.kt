package com.example.domain.usecase
import com.example.domain.abstraction.Repo.Repo
import com.example.domain.entity.RegisterRequest
import com.example.domain.entity.RegisterResponse

class SignUpUseCase(val repo: Repo) {
    suspend operator fun invoke(request: RegisterRequest): RegisterResponse {
        return repo.register(request)
    }
}