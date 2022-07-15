package com.tanhoang.com.model.employee

enum class Role(val role: Int) {
    RESPONDENT(1),
    MANAGER(2),
    DIRECTOR(3);

    companion object {
        fun fromInt(value: Int) = values().firstOrNull { it.role == value } ?: RESPONDENT
    }
}
