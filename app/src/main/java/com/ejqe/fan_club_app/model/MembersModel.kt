package com.ejqe.fan_club_app.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize

data class MembersModel(
    val _name: String?= null,
    var age: String?= null,
    val birthdate: String?= null,
    val birthplace: String?= null,
    val bloodType: String?= null,
    val fullName: String?= null,
    val generation: String?= null,
    val height: String?= null,
    val imageUrl: String?= null

) : Parcelable
