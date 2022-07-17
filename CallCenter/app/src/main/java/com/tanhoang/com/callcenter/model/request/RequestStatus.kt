package com.tanhoang.com.callcenter.model.request

enum class RequestStatus(status: Int) {
    NOT_HANDLED(0),
    BEING_HANDLED(1),
    COMPLETED(2)
}
