package ir.org.tavanesh.data.impl.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.exam.entity.Exam
import ir.org.tavanesh.core.domain.exam.repository.AnswerExamParams
import ir.org.tavanesh.core.domain.exam.repository.ExamRepository
import ir.org.tavanesh.core.domain.exam.repository.GetExamListParams
import ir.org.tavanesh.core.domain.exam.repository.GetExamParams
import ir.org.tavanesh.core.domain.question.entity.AnswerType
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.ParseFailure
import ir.org.tavanesh.data.datasource.ExamRemoteDataSource
import ir.org.tavanesh.data.models.ExamAnswerRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class ExamRepositoryImpl @Inject constructor(
    private val examRemoteDataSource: ExamRemoteDataSource,
): ExamRepository {
    override suspend fun getExamList(params: GetExamListParams): Result<List<Exam>, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = examRemoteDataSource.getExamList(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun getExam(params: GetExamParams): Result<Exam, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = examRemoteDataSource.getExam(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun answerExam(params: AnswerExamParams): Result<String, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val answerExam = JSONArray()
                params.questions.forEach {
                    val ob = JSONObject()
                    ob.put("questionId",it.id.toInt())
                    ob.put("answerType",it.type.status)
                    when(it.type){
                        AnswerType.TEXT ->{
                            ob.put("answer",it.answer)
                        }
                        AnswerType.SINGLE_SELECT ->{
                            val answer = it.answers?.first { it.isSelect }?.id?.toInt()
                            ob.put("answer" ,answer)
                        }
                        AnswerType.MULTI_SELECT ->{
                            val list = mutableListOf<Int>()
                            it.answers?.forEach { item ->
                                if(item.isSelect) list.add(item.id.toInt())
                            }
                            ob.put("answer",list)
                        }
                    }
                    answerExam.put(ob)
                }
                val result = examRemoteDataSource.answerExam(
                    ExamAnswerRequest(params.examId, answerExam.toString())
                )
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            } catch (e:JSONException){
                Result.error(ParseFailure())
            }
        }
    }

}