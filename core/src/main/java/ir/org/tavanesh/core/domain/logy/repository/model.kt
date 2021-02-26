package ir.org.tavanesh.core.domain.logy.repository

import ir.org.tavanesh.core.domain.logy.entity.Logy

data class CreateLogyParams(
    var logy:Logy,
)

data class SendLogysParams(
    var x:String?=null,
)