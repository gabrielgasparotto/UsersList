package com.example.features.app.extensions

import android.view.View

fun View.gone() = run { this.visibility = View.GONE }
fun View.visible() = run { this.visibility = View.VISIBLE }