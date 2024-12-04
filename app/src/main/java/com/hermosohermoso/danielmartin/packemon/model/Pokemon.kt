/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hermosohermoso.danielmartin.packemon.model

import androidx.annotation.DrawableRes

/**
 * [Pokemon] la clase de datos para representar los pokemon y sus caracterisctias
 */
data class Pokemon(
    val pokeId: Int,
    val imageId: Int,
    val nombre: Int,
    @DrawableRes val tipo: Int,
    val atq1: Int,
    val atq2: Int,
)
