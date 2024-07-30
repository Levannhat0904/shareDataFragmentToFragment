package net.braniumacademy.lesson513.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Country(
    @JsonProperty("name") var name: String = "",
    @JsonProperty("flag") var flag: String = "",
    @JsonProperty("capital") var capital: String = "",
    @JsonProperty("population") var population: Float = 0f,
    @JsonProperty("density") var density: Int = 0,
    @JsonProperty("area") var area: Int = 0,
    @JsonProperty("world_share") var worldShare: String = ""
)