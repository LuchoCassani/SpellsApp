package com.cassani.phrasesapp.data.model

import com.google.gson.annotations.SerializedName

data class SpellModel (
    @SerializedName ("hechizo") val spell:String,
    @SerializedName ("uso") val use:String)