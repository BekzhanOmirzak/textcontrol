package com.app.panica.presentation.utils

import timber.log.Timber

object VLog {

    fun d(message: String, tag: String = LOG_TAG_GL) {
        Timber.tag(tag).d(message)
    }

    fun e(message: String,tag:String = LOG_TAG_GL) {
        Timber.tag(tag).e(message)
    }


}
