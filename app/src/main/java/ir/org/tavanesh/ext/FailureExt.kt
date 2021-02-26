package ir.org.tavanesh.ext
import android.content.Context
import ir.org.tavanesh.R
import ir.org.tavanesh.core.utils.*

fun Failure.getMessage(context:Context):String{
    when (this) {
        is ProviderFailure -> {
            return when (this.type) {
                provider_internet->{
                    context.getString(R.string.error_check_internet_connection)
                }
                provider_storage -> {
                    context.getString(R.string.error_storage_permission)
                }
                provider_location -> {
                    context.getString(R.string.error_location_permission)
                }
                provider_voice_recorder -> {
                    context.getString(R.string.error_voice_recorder_permission)
                }
                else -> {
                    context.getString(R.string.error_unknown)
                }
            }
        }
        is ServerFailure -> {
            return context.getString(R.string.error_server_out_of_reach)
        }
        is InvalidInputFailure -> {
            return context.getString(R.string.error_invalid_input)
        }
        is ResponseFailure -> {
            this.message?.let {
                return it
            } ?: run {
                return context.getString(R.string.error_server_out_of_reach)
            }
        }
        is NoParamsFailure -> {
            return context.getString(R.string.error_no_params)
        }
        is UnKnownFailure -> {
            return context.getString(R.string.error_unknown)
        }
        is AuthFailure -> {
            return context.getString(R.string.error_invalid_token)
        }
        is BanFailure -> {
            return context.getString(R.string.error_you_are_ban)
        }
        is ParseFailure -> {
            return context.getString(R.string.error_parse_failure)
        }
    }
}