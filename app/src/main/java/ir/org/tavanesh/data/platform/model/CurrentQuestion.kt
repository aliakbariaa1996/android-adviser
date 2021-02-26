package ir.org.tavanesh.data.platform.model

import ir.org.tavanesh.core.domain.question.entity.Question


data class CurrentQuestion (
    var index:Int = 0,
    var question: Question?=null,
)