package ir.org.tavanesh.core.utils

sealed class Failure: Exception()

class ProviderFailure(
    val type: String? = provider_internet
):Failure()

class ServerFailure:Failure()

class InvalidInputFailure :Failure()

class ResponseFailure:Failure()

class NoParamsFailure:Failure()

class UnKnownFailure:Failure()

class AuthFailure:Failure()

class BanFailure:Failure()

class ParseFailure:Failure()

const val provider_internet = "provider_internet"
const val provider_location = "provider_location"
const val provider_storage = "provider_storage"
const val provider_voice_recorder = "provider_voice_recorder"


const val server_success = 200
const val server_error = 500
const val server_auth = 401
const val server_validation = 422
const val server_auth_2 = 403
const val server_wrong = 404
const val server_done_before = 404
