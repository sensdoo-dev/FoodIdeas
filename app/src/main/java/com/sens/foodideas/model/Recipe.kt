package com.sens.foodideas.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recipe(
    @DrawableRes val imageRes: Int,
    @StringRes val nameRes: Int,
    @StringRes val descRes: Int
)
