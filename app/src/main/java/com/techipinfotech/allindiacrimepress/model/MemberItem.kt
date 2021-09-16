package com.techipinfotech.allindiacrimepress.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "members")
data class MemberItem(

    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("member_id")
    val memberId: String? =null,
    @SerializedName("name")
    val name: String? =null,
    @SerializedName("fname")
    val fName: String? =null,
    @SerializedName("photo")
    val photo: String? =null,
    @SerializedName("gender")
    val gender: String? =null,
    @SerializedName("dob")
    val dob: String? =null,
    @SerializedName("mobile")
    val mobile: String? =null,
    @SerializedName("email")
    val email: String? =null,
    @SerializedName("bloodgroup")
    val bloodGroup: String? =null,
    @SerializedName("addharno")
    val addharNo: String? =null,
    @SerializedName("panno")
    val panNo: String? =null,
    @SerializedName("qualification")
    val qualification: String? =null,
    @SerializedName("profession")
    val profession: String? =null,
    @SerializedName("caddress")
    val cAddress: String? =null,
    @SerializedName("paddress")
    val pAddress: String? =null,
    @SerializedName("city")
    val city: String? =null,
    @SerializedName("state")
    val state: String? =null,
    @SerializedName("country")
    val country: String? =null,
    @SerializedName("pinno")
    val pinNo: String? =null,
    @SerializedName("chequename")
    val chequeName: String? =null,
    @SerializedName("accountno")
    val accountNo: String? =null,
    @SerializedName("bankname")
    val bankName: String? =null,
    @SerializedName("branch")
    val branch: String? =null,
    @SerializedName("accounttype")
    val accountType: String? =null,
    @SerializedName("ifsccode")
    val ifscCode: String? =null,
    @SerializedName("nomineename")
    val nomineeName: String? =null,
    @SerializedName("sponser_id")
    val sponserId: String? =null,
    @SerializedName("password")
    val password: String? =null,
    @SerializedName("remark")
    val remark: String? =null,
    @SerializedName("joiningdate")
    val joiningDate: String? =null,
    @SerializedName("qrcodefile")
    val qrCodeFile: String? =null,
    @SerializedName("post")
    val post: String? =null
)