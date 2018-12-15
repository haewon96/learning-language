package org.sungshin.lnk.learningnorthkorean.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_solve_quiz.*
import org.sungshin.lnk.learningnorthkorean.R
import org.sungshin.lnk.learningnorthkorean.`object`.Problem
import java.util.ArrayList

class QuizLevel1Activity :AppCompatActivity() {

    var stage1 = ArrayList<Problem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solve_quiz)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        quizList();
        tv_question.setText(stage1.get(0).getQusetion());
    }

    fun quizList() {
        stage1.add(Problem("‘요금’의 북한어는 ‘드는 돈’이다.", false, "요금의 북한 말은 료금이다.", "요금 내고 가세요."))
        stage1.add(Problem("‘왜놈’의 북한어는 ‘왜놈’이다.", true, "", "왜놈들이다! 왜놈들이다!"))
        stage1.add(Problem("‘올림픽’의 북한어는 ‘올림픽’이다.", true, "", "이번 올림픽 정말 기대되지 않니?"))
        stage1.add(Problem("‘예감’의 북한어는 ‘례감’이다.", false, "에감의 북한어는 예감이다.", "오늘은 왠지 예감이 좋습니다."))
        stage1.add(Problem("‘영장’의 북한어는 ‘령장’이다.", true, "", "령장 먼저 들고 오시죠."))
        stage1.add(Problem("‘하나의 고비로 되는 높은 단계’라는 북한말은 ‘영마루’이다.", false, "령마루이다.", "세월의 령마루를 넘고 넘다."))
        stage1.add(Problem("‘열중’의 북한어는 ‘열중’이다.", true, "", "어제는 독서에 열중하고 있었습니다."))
        stage1.add(Problem("북한에서 ‘열흘’은 ‘열 날’을 뜻한다.", false, "남한의 뜻이다.", "열흘동안만 쉬고 오겠습니다."))
        stage1.add(Problem("북한에서 ‘요람’은 ‘따뜻한 어버이 사랑과 보살핌으로 행복하게 생활하며 즐길 수 있게 마련된 장소’를 뜻한다.", true, "", "지금 기분이 마치 요람과 같습니다."))
        stage1.add(Problem("‘염두’의 북한어는 ‘념두’이다.", false, "염두의 북한어는 념두이다.", "결혼식 선물로 이 꽃을 념두에 두고 있었습니다."))
        stage1.add(Problem("‘열사’의 북한어는 ‘렬사’이다.", false, "열사의 북한어는 렬사이다.", "그는 마치 렬사의 모습이었다."))
        stage1.add(Problem("‘그 해의 첫머리’를 뜻하는 단어는 남한어로 ‘년초’ 북한어로 ‘연초’이다.", false, "남한어가 연초, 북한어가 년초에 해당한다.", "년초라서 손님이 많은 편입니다."))
        stage1.add(Problem("‘연일’의 북한어는 ‘련일’이다.", true, "", "련일 그런 일만 당하니!"))
        stage1.add(Problem("‘연락병’은 북한에서 ‘련락뼝’이라고 발음한다.", true, "", "연락병이 편지를 가지고 왔습니다."))
    }
}