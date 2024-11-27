import Model.ViaCep
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


suspend fun fetchPosts(cep: String): ViaCep {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
    return try {
        client.get("https://viacep.com.br/ws/$cep/json/").body()
    } finally {
        client.close()
    }
}

fun main() {
    while(true){
        println("Digite um cep: ")
        var cep = readLine()?.replace(Regex("\\D"),"")

        if(cep.isNullOrEmpty() || cep.length != 8 || !cep.all{it.isDigit()}){
            println("Cep inválido!")
            continue
        }
        kotlinx.coroutines.runBlocking {
            var info = fetchPosts(cep.replace(Regex("\\D"),""));
            println("CEP: ${info.cep}")
            println("LOGRADOURO: ${info.logradouro}")
            println("COMPLEMENTO: ${info.complemento}")
            println("UNIDADE: ${info.unidade}")
            println("BAIRRO: ${info.bairro}")
            println("LOCALIDADE: ${info.localidade}")
            println("UF: ${info.uf}")
            println("ESTADO: ${info.estado}")
            println("REGIÃO: ${info.regiao}")
            println("DDD: ${info.ddd}")
            println("SIAFI: ${info.siafi}")
        }
    }
}
