package com.widyatama.core.data.network


import com.widyatama.core.BuildConfig
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.AppLoggerUtil
import okhttp3.*
import okio.Buffer
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit


object HttpClient : OkHttpClient() {

//    var sessionHelper = SessionHelper()

    var urlLog : String? = null
    var headersLog : String? = null
    var bodyLog : String? = null
    var methodLog : String? = null
    var statusCodeLog : Int? = null

    var charset : Charset? = Charsets.UTF_8

    internal class HttpInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()

//            val token = sessionHelper.getSession("Token", "token")
            val token = "Token"
            val requestBuilder = original.newBuilder()
                    .addHeader("Token", token)
            val request = requestBuilder.build()

            val t1 = System.nanoTime()
            getRequestValue(request)

            val response = chain.proceed(request)

            // Get Execution Time
            val t2 = System.nanoTime()
            val executionTime = TimeUnit.MINUTES.convert((t2 - t1), TimeUnit.MILLISECONDS)

            // Get Response
            val buffer = getResponseValue(response)

            if (response.body()?.contentLength() != 0L) {
                val message = "Request ($methodLog) : $urlLog \n[Body] => \n$bodyLog \n[Headers] => \n$headersLog" +
                        "Excecution Time : $executionTime" +
                        "\nResponse ($statusCodeLog) : " + buffer?.clone()?.readString(charset);

                if (BuildConfig.DEBUG){
                    AppLoggerUtil.i(message)
                }
            }

            return response
        }
    }

    internal class HeaderInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val ongoing = chain.request().newBuilder()
            ongoing.addHeader("Accept", "application/json")
            ongoing.addHeader("Content-Type", "application/json")
            val newRequest = ongoing.build()
            return onOnIntercept(chain, newRequest)
        }

        @Throws(IOException::class)
        private fun onOnIntercept(chain: Interceptor.Chain, request: Request): Response {
            return try {
                chain.proceed(request)
            } catch (e: SocketTimeoutException) {
                val response = chain.proceed(chain.request())
                response.newBuilder().body(ResponseBody.create(response.body()?.contentType(), "Host tidak ditemukan")).build()
            } catch (e: UnknownHostException) {
                val response = chain.proceed(chain.request())
                response.newBuilder().body(ResponseBody.create(response.body()?.contentType(), "Host tidak ditemukan")).build()
            }
        }
    }

    private fun getRequestValue(value : Request){
        urlLog = value.url().toString()
        headersLog = value.headers().toString()
        bodyLog = getBodyRequestValue(value.body())
        methodLog = value.method()
    }

    private fun getResponseValue(value : Response) : Buffer? {
        val source = value.body()?.source()
        source?.request(Long.MAX_VALUE) // Buffer the entire body.
        var buffer = source?.buffer()

        val contentType = value.body()?.contentType()

        if (contentType != null) {
            charset = contentType.charset(Charsets.UTF_8)
        }

        statusCodeLog = value.code()

        return buffer
    }

    private fun getBodyRequestValue(value : RequestBody?) : String {
        return try {
            val buffer = Buffer()
            value?.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: IOException) {
            "Read Body Request Failed"
        }
    }

    fun crudClient() : OkHttpClient {
        return OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(HeaderInterceptor())
                .addInterceptor(HttpInterceptor())
                .build()
    }

    fun uploadClient() : OkHttpClient {
        return OkHttpClient().newBuilder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(HttpInterceptor())
                .build()
    }
}