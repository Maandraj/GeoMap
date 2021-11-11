package com.maandraj.geomap.features.map.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PropertiesRes(
    @Json(name = "abbrev")
    val abbrevRes: String,
    @Json(name = "abbrev_len")
    val abbrevLenRes: Int,
    @Json(name = "adm0_a3")
    val adm0A3Res: String,
    @Json(name = "adm0_a3_is")
    val adm0A3IsRes: String,
    @Json(name = "adm0_a3_un")
    val adm0A3UnRes: Int,
    @Json(name = "adm0_a3_us")
    val adm0A3UsRes: String,
    @Json(name = "adm0_a3_wb")
    val adm0A3WbRes: Int,
    @Json(name = "adm0_dif")
    val adm0DifRes: Int,
    @Json(name = "admin")
    val adminRes: String,
    @Json(name = "brk_a3")
    val brkA3Res: String,
    @Json(name = "brk_diff")
    val brkDiffRes: Int,
    @Json(name = "brk_group")
    val brkGroupRes: String?,
    @Json(name = "brk_name")
    val brkNameRes: String,
    @Json(name = "continent")
    val continentRes: String,
    @Json(name = "economy")
    val economyRes: String,
    @Json(name = "featurecla")
    val featureclaRes: String,
    @Json(name = "filename")
    val filenameRes: String,
    @Json(name = "fips_10_")
    val fips10Res: String,
    @Json(name = "formal_en")
    val formalEnRes: String,
    @Json(name = "formal_fr")
    val formalFrRes: String?,
    @Json(name = "gdp_md_est")
    val gdpMdEstRes: Int,
    @Json(name = "gdp_year")
    val gdpYearRes: Int,
    @Json(name = "geou_dif")
    val geouDifRes: Int,
    @Json(name = "geounit")
    val geounitRes: String,
    @Json(name = "gu_a3")
    val guA3Res: String,
    @Json(name = "homepart")
    val homepartRes: Int,
    @Json(name = "income_grp")
    val incomeGrpRes: String,
    @Json(name = "iso_a2")
    val isoA2Res: String,
    @Json(name = "iso_a3")
    val isoA3Res: String,
    @Json(name = "iso_n3")
    val isoN3Res: String,
    @Json(name = "labelrank")
    val labelrankRes: Int,
    @Json(name = "lastcensus")
    val lastcensusRes: Int,
    @Json(name = "level")
    val levelRes: Int,
    @Json(name = "long_len")
    val longLenRes: Int,
    @Json(name = "mapcolor13")
    val mapcolor13Res: Int,
    @Json(name = "mapcolor7")
    val mapcolor7Res: Int,
    @Json(name = "mapcolor8")
    val mapcolor8Res: Int,
    @Json(name = "mapcolor9")
    val mapcolor9Res: Int,
    @Json(name = "name")
    val nameRes: String,
    @Json(name = "name_alt")
    val nameAltRes: String?,
    @Json(name = "name_len")
    val nameLenRes: Int,
    @Json(name = "name_long")
    val nameLongRes: String,
    @Json(name = "name_sort")
    val nameSortRes: String,
    @Json(name = "note_adm0")
    val noteAdm0Res: String?,
    @Json(name = "note_brk")
    val noteBrkRes: String?,
    @Json(name = "pop_est")
    val popEstRes: Int,
    @Json(name = "pop_year")
    val popYearRes: Int,
    @Json(name = "postal")
    val postalRes: String,
    @Json(name = "region_un")
    val regionUnRes: String,
    @Json(name = "region_wb")
    val regionWbRes: String,
    @Json(name = "scalerank")
    val scalerankRes: Int,
    @Json(name = "sov_a3")
    val sovA3Res: String,
    @Json(name = "sovereignt")
    val sovereigntRes: String,
    @Json(name = "su_a3")
    val suA3Res: String,
    @Json(name = "su_dif")
    val suDifRes: Int,
    @Json(name = "subregion")
    val subregionRes: String,
    @Json(name = "subunit")
    val subunitRes: String,
    @Json(name = "tiny")
    val tinyRes: Int,
    @Json(name = "type")
    val typeRes: String,
    @Json(name = "un_a3")
    val unA3Res: String,
    @Json(name = "wb_a2")
    val wbA2Res: String,
    @Json(name = "wb_a3")
    val wbA3Res: String,
    @Json(name = "wikipedia")
    val wikipediaRes: Int,
    @Json(name = "woe_id")
    val woeIdRes: Int,
    @Json(name = "woe_id_eh")
    val woeIdEhRes: Int,
    @Json(name = "woe_note")
    val woeNoteRes: String
)