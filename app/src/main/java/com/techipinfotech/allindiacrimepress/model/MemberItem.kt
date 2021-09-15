package com.techipinfotech.allindiacrimepress.model


import com.google.gson.annotations.SerializedName

data class MemberItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("member_id")
    val memberId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("fname")
    val fName: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("dob")
    val dob: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("bloodgroup")
    val bloodGroup: String,
    @SerializedName("addharno")
    val addharNo: String,
    @SerializedName("panno")
    val panNo: String,
    @SerializedName("qualification")
    val qualification: String,
    @SerializedName("profession")
    val profession: String,
    @SerializedName("caddress")
    val cAddress: String,
    @SerializedName("paddress")
    val pAddress: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("pinno")
    val pinNo: String,
    @SerializedName("chequename")
    val chequeName: String,
    @SerializedName("accountno")
    val accountNo: String,
    @SerializedName("bankname")
    val bankName: String,
    @SerializedName("branch")
    val branch: String,
    @SerializedName("accounttype")
    val accountType: String,
    @SerializedName("ifsccode")
    val ifscCode: String,
    @SerializedName("nomineename")
    val nomineeName: String,
    @SerializedName("sponser_id")
    val sponserId: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("remark")
    val remark: String,
    @SerializedName("joiningdate")
    val joiningDate: String,
    @SerializedName("qrcodefile")
    val qrCodeFile: String,
    @SerializedName("post")
    val post: String
)