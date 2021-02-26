package ir.org.tavanesh.data.mappers

import ir.org.tavanesh.core.domain.exam.entity.Exam
import ir.org.tavanesh.data.models.ExamModel

fun ExamModel.toExam(): Exam {
    return Exam(
        id = this.id,
        name = this.name,
        description = this.description,
        image = this.image,
        questionCount = this.questionCount,
        testTime = this.testTime,
        centerId = this.centerId,
        questions = null,
    )
}

fun List<ExamModel>.toExam(): List<Exam> {
    val list = mutableListOf<Exam>()
    this.let {
        it.forEach { item ->
            list.add(item.toExam())
        }
    }
    return list
}