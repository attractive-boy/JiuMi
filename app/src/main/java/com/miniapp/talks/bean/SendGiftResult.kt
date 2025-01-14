package com.miniapp.talks.bean

data class SendGiftResult(
    var code: Int,
    var `data`: GiftResult,
    var message: String
)

data class GiftResult(
    var push: List<PushBean.DataBean>,
    var users: List<User>
)

//data class Push(
//    var from_name: String,
//    var gift_name: String,
//    var img: String,
//    var num: String,
//    var uid: String,
//    var user_name: String
//)

data class User(
    var headimgurl: String,
    var is_first: Int,
    var nick_color: String,
    var nickname: String,
    var userId: String
)