package com.nick_sib.popularlibraries



fun String.getUserName(): String  = this.substringBefore('/')
