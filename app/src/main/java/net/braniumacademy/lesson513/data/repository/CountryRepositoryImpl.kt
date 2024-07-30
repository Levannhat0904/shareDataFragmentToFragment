package net.braniumacademy.lesson513.data.repository

import android.content.Context
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import net.braniumacademy.lesson513.R
import net.braniumacademy.lesson513.data.model.Country
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader
import java.io.StringWriter
import java.io.Writer
import java.nio.charset.StandardCharsets

class CountryRepositoryImpl(private val context: Context) : CountryRepository {
    private val jsonString: String
        get() {
            val writer: Writer = StringWriter()
            val buffer = CharArray(1024)
            try {
                context.resources.openRawResource(R.raw.country).use { inputStream ->
                    val reader: Reader = BufferedReader(
                        InputStreamReader(inputStream, StandardCharsets.UTF_8)
                    )
                    var size: Int
                    while (reader.read(buffer).also { size = it } != -1) {
                        writer.write(buffer, 0, size)
                    }
                }
            } catch (ignored: IOException) {
            }
            return writer.toString()
        }

    override fun getCountries(): List<Country> {
        val jsonString = jsonString
        val mapper = ObjectMapper()
        return try {
            mapper.readValue(
                jsonString,
                object : TypeReference<List<Country>>() {})
        } catch (ignored: JsonProcessingException) {
            listOf()
        }
    }
}