package org.sungshin.lnk.learningnorthkorean.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_solve_quiz.*
import org.sungshin.lnk.learningnorthkorean.R
import org.sungshin.lnk.learningnorthkorean.`object`.Problem
import java.util.ArrayList

class QuizLevel3Activity:AppCompatActivity() {
    var stage3 = ArrayList<Problem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solve_quiz)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        quizList();
        tv_question.setText(stage3.get(0).getQusetion());
    }

    fun quizList() {
        stage3.add(Problem("‘심리’는 북한어로 ‘심니’라고 발음한다.", false, "심리는 북한어로 '심리'라고 발음한다.", ""))
        stage3.add(Problem("‘실적’은 북한어와 남한어의 뜻이 같다.", true, "", ""))
        stage3.add(Problem("‘실망’은 북한어와 남한어의 뜻이 같다.", true, "", ""))
        stage3.add(Problem("‘스포츠’의 북한어는 ‘운동’이다.", false, "스포츠의 북한어는 스포츠이다.", ""))
        stage3.add(Problem("‘시골’은 북한어와 남한어의 뜻이 같다.", true, "", ""))
        stage3.add(Problem("‘시아버지’는 북한에 없는 말이다.", false, "북한에서도 시아버지라는 말을 사용한다.", ""))
        stage3.add(Problem("‘식’은 북한어로 ‘멋’을 의미한다.", true, "", ""))
        stage3.add(Problem("‘식품’은 북한어로 ‘식료품’을 의미한다.", true, "", ""))
        stage3.add(Problem("‘남새’의 남한어는 ‘냄새’이다.", false, "남새는 남한어로 채소를 의미한다.", ""))
        stage3.add(Problem("‘코미디’의 북한어는 ‘웃음제조’이다.", false, "코미디의 북한어는 웃음극이다.", ""))
        stage3.add(Problem("‘초식동물’은 북한어와 남한어 공통으로 사용한다.", false, "초식동물의 북한어는 풀먹이동물이다.", ""))
        stage3.add(Problem("북한에는 ‘오레미’라는 단어가 있다.", true, "오레미는 남한어로 올케를 의미한다.", ""))
        stage3.add(Problem("‘잔돈’은 북한어와 남한어 공통으로 사용한다.", false, "잔돈의 북한어는 부스럭돈이다.", ""))
        stage3.add(Problem("‘오목샘’은 남한어로 ‘우물’이다.", false, "오목샘의 남한어는 보조개이다.", ""))
        stage3.add(Problem("‘되새기다’는 북한어로 ‘곱새기다’이다.", true, "", ""))
        stage3.add(Problem("‘도순도순’의 남한어는 ‘오손도손’이다.", true, "", ""))
        stage3.add(Problem("‘쌀밥’의 북한어는 ‘조밥’이다.", false, "쌀밥의 북한어는 이밥이다.", ""))
    }
}