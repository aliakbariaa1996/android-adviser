package ir.org.tavanesh.data.datasource

interface AuthLocalDataSource {
    suspend fun getToken():String

    suspend fun setToken(token:String):Boolean

    suspend fun logout():Boolean

    suspend fun getFireBaseToken():String

    suspend fun setFireBaseToken(token:String):Boolean
}