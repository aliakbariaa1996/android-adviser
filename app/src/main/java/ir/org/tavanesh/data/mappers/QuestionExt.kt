package ir.org.tavanesh.data.mappers

import ir.org.tavanesh.core.domain.question.entity.Answer
import ir.org.tavanesh.core.domain.question.entity.AnswerType
import ir.org.tavanesh.core.domain.question.entity.Question
import ir.org.tavanesh.data.models.AnswerModel
import ir.org.tavanesh.data.models.QuestionModel

fun QuestionModel.toQuestion(): Question {
    return Question(
        id = this.id,
        question = this.title,
        answers = this.answers?.toAnswer(),
        isRequired = this.isRequired,
        type = AnswerType.valueOf(this.type),
        secondToShow = this.timeView,
    )
}

fun List<QuestionModel>.toQuestion(): List<Question> {
    val list = mutableListOf<Question>()
    this.let {
        it.forEach { item ->
            list.add(item.toQuestion())
        }
    }
    return list
}

fun AnswerModel.toAnswer(): Answer {
    return Answer(
        id = this.id,
        answer = this.title,
        questionId = this.questionId,
    )
}

fun List<AnswerModel>.toAnswer(): List<Answer> {
    val list = mutableListOf<Answer>()
    this.let {
        it.forEach { item ->
            list.add(item.toAnswer())
        }
    }
    return list
}
