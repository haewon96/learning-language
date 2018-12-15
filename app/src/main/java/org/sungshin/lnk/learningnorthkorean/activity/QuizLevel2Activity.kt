package org.sungshin.lnk.learningnorthkorean.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_solve_quiz.*
import org.sungshin.lnk.learningnorthkorean.R
import org.sungshin.lnk.learningnorthkorean.`object`.Problem
import java.util.ArrayList

class QuizLevel2Activity :AppCompatActivity(){
    var stage2 = ArrayList<Problem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solve_quiz)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        quizList();
        tv_question.setText(stage2.get(0).getQusetion());
    }

    fun quizList() {
        stage2.add(Problem("‘연기자’의 북한어는 ‘련기자’이다.", false, "", "련기자의 북한어는 연기자이다."))
        stage2.add(Problem("‘연구하다’의 북한어는 ‘련구하다’이다.", false, "", "연구하다의 북한어는 연구하다."))
        stage2.add(Problem("‘옆디다’의 북한어는 ‘엎디다’이다.", true, "", ""))
        stage2.add(Problem("‘영역’의 북한 말은 ‘령역’이다.", true, "", ""))
        stage2.add(Problem("북한에서 ‘영수증’은 ‘령수쯩’이라고 발음한다.", true, "", ""))
        stage2.add(Problem("‘전날에, 삯일을 하는 사람’은 북한어로 ‘노동자’이다.", false, "노동자가 아니라 '역군'이다.", ""))
        stage2.add(Problem("‘엉거주춤’의 북한어는 ‘엉거주춤이다.", true, "", ""))
        stage2.add(Problem("‘얻어’는 북한어로 ‘우연히’를 뜻한다.", true, "", ""))
        stage2.add(Problem("‘억지로’는 북한어로 ‘억지로’이다.", true, "", ""))
        stage2.add(Problem("‘영상’의 북한어는 ‘령상’이다.", false, "영상의 북한어는 영상이다.", ""))
        stage2.add(Problem("‘어머님’의 북한어는 ‘오마니’이다.", false, "어머님의 북한어는 어머님이다.", ""))
        stage2.add(Problem("‘안기다’는 북한어로 ‘힘껏 들이치거나 박다’를 뜻한다.", true, "", ""))
        stage2.add(Problem("‘도넛’은 북한어로 ‘구멍빵’이다.", false, "도넛의 북한어는 가락지빵이다.", ""))
        stage2.add(Problem("‘주스’는 북한어로 ‘과일물’이다.", false, "주스의 북한어는 과일단물이다.", ""))
        stage2.add(Problem("‘화장실’은 북한어로 ‘청결실’이다.", false, "화장실의 북한어는 위생실이다.", ""))
        stage2.add(Problem("‘바쁘다’의 북한어는 ‘일없다’이다.", false, "바쁘다의 북한어는 어렵다이다.", ""))
        stage2.add(Problem("‘요리’의 북한어는 ‘요리’이다.", false, "요리의 북한어는 료리이다.", ""))
        stage2.add(Problem("‘예년’의 북한어는 ‘례년’이다.", true, "", ""))
    }
}