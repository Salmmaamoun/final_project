package com.example.data.repo.datasource


import android.util.Log
import com.example.data.remote.AiHighlightService
import com.example.data.remote.AiService
import com.example.data.remote.ApiSemanticService
import com.example.data.remote.ApiService
import com.example.domain.abstraction.DataSourceRepo.DataSourceRepo
import com.example.domain.entity.AyaData
import com.example.domain.entity.HighlightResponce
import com.example.domain.entity.LexicalResponseItem
import com.example.domain.entity.LoginRequest
import com.example.domain.entity.RegisterRequest
import com.example.domain.entity.LoginResponse
import com.example.domain.entity.QuestionRequest
import com.example.domain.entity.RegisterResponse
import com.example.domain.entity.SemanticAiResponce
import com.example.domain.entity.SemanticApiResponse
import com.example.domain.entity.SurahResponse
import com.example.domain.entity.TafseerResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody

class DataSourceImp(val apiSevice: ApiService , val aiService: AiService , val apiSemanticService : ApiSemanticService , val aiHighlightService: AiHighlightService): DataSourceRepo {
    override suspend fun login(request: LoginRequest): LoginResponse {
        val email = RequestBody.create("text/plain".toMediaTypeOrNull(), request.email)
        val password = RequestBody.create("text/plain".toMediaTypeOrNull(), request.password)
        return apiSevice.login(email, password)
    }

    override suspend fun register(request: RegisterRequest): RegisterResponse {
        val name = RequestBody.create("text/plain".toMediaTypeOrNull(), request.name)
        val email = RequestBody.create("text/plain".toMediaTypeOrNull(), request.email)
        val password = RequestBody.create("text/plain".toMediaTypeOrNull(), request.password)
        val passcon=RequestBody.create("text/plain".toMediaTypeOrNull(), request.password_confirmation)
        val gender = RequestBody.create("text/plain".toMediaTypeOrNull(), request.gender)
        val phone = RequestBody.create("text/plain".toMediaTypeOrNull(), request.phone)

        val imagePart = request.image?.let {
            val requestImage = RequestBody.create("image/jpeg".toMediaTypeOrNull(), it)
            MultipartBody.Part.createFormData("image", "image.jpg", requestImage)
        }

        return apiSevice.registerUser(name, email, password, password_confirmation = passcon  , gender, phone, imagePart)
    }

    override suspend fun getSurahs(language: String):SurahResponse {
        return apiSevice.getSurahs(language)
    }

    override suspend fun getAyah(
        editionId: Int,
        surahNumber: Int,
        ayahNumber: Int
    ): TafseerResponse {
        return apiSevice.getAyah(editionId,surahNumber,ayahNumber)
    }

    override suspend fun getSearchLexical(term: String): List<LexicalResponseItem> {
        return apiSevice.getSearchLexical(term)
    }

    override suspend fun getSemanticsearch(query: String): SemanticAiResponce {
        val response = aiService.getSemaSearch(query)
        if (response.isSuccessful) {
            val responseBody: ResponseBody? = response.body()
            val responseText = withContext(Dispatchers.IO) {
                responseBody?.string()
            }
            Log.d("aiResponse", responseText.toString())
        } else {
            Log.e("aiResponse", "Error: ${response.errorBody()?.string()}")
        }
        return aiService.getSemanticSearch(query)
    }

    override suspend fun getSmanticAyaSearch(query: Int): AyaData {
           return apiSemanticService.getSemanticAyaById(query).ayaData ?:AyaData()
    }

    override suspend fun getHighligthData(question: String , context :String): HighlightResponce {
        val output= aiHighlightService.askQuestion(
            question ,context
        )
        Log.d("highligth request", "$question, $context")
        Log.d("highligth response",output.toString())
        return output
    }
}