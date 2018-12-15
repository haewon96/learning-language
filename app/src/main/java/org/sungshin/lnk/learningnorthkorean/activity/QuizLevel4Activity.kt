package org.sungshin.lnk.learningnorthkorean.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_solve_quiz.*
import org.sungshin.lnk.learningnorthkorean.R
import org.sungshin.lnk.learningnorthkorean.`object`.Problem
import java.util.ArrayList

class QuizLevel4Activity:AppCompatActivity() {
    var stage4 = ArrayList<Problem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solve_quiz)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        quizList();
        tv_question.setText(stage4.get(0).getQusetion());
    }

    fun quizList() {
        stage4.add(Problem("‘추수’의 북한어는 ‘가을걷이’이다.", true, "", ""))
        stage4.add(Problem("‘틀림없이’의 북한어는 ‘고민없이’이다.", false, "틀림없이의 북한어는 '거의없이'이다.", ""))
        stage4.add(Problem("북한 사투리 ‘사자고추’는 ‘파프리카’를 뜻한다.", false, "사자고추의 남한어는 피망이다.", ""))
        stage4.add(Problem("‘골키퍼’의 북한어는 ‘막대장’이다.", false, "골키퍼의 북한어는 문지기이다.", ""))
        stage4.add(Problem("‘미소’의 북한어는 ‘볼웃음’이다.", true, "", ""))
        stage4.add(Problem("‘코너킥’의 북한어는 ‘구석차기’이다.", true, "", ""))
        stage4.add(Problem("‘빙수’의 북한어는 ‘간얼음’이다.", false, "빙수의 북한어는 단얼음이다.", ""))
        stage4.add(Problem("‘메리야스’의 북한어는 ‘속옷’이다.", false, "메리야스의 북한어는 뜨게옷이다.", ""))
        stage4.add(Problem("‘카스테라’의 북한어는 ‘노란빵’이다.", false, "카스테라의 북한어는 설기과자이다.", ""))
        stage4.add(Problem("‘단묵’의 남한어는 ‘도토리묵’이다.", false, "단묵의 남한어는 젤리이다.", ""))
        stage4.add(Problem("‘동양화’의 북한어는 ‘동가화’이다.", false, "동양어의 북한어는 조선화이다.", ""))
        stage4.add(Problem("‘나리옷’의 남한어는 ‘원피스’이다.", false, "나리옷의 남한어는 드레스이다.", ""))
        stage4.add(Problem("‘색동다리’의 남한어는 ‘저고리’이다.", false, "색동다리의 남한어는 무지개이다.", ""))
        stage4.add(Problem("‘영웅주의’는 남한에서 노동계급의 당과 수령을 위하여, 조국과 인민을 위하여 무한한 헌신성과 용감성을 발휘하는 고상한 사상과 행동’을 뜻한다.", false, "이 뜻은 북한어에만 해당되는 뜻이다.", ""))
        stage4.add(Problem("‘스스로’는 북한에서 ‘그대로’를 뜻한다.", false, "스스로는 북한에서 저절로라는 뜻이 있다.", ""))
        stage4.add(Problem("‘승리’는 북한에서 ‘승니’라고 발음한다.", false, "승리는 북한어로 '승:리'라고 발음한다.", ""))
        stage4.add(Problem("‘승낙’은 북한의 법률 용어이다.", false, "승낙은 남한에서만 법률 용어로 사용된다.", ""))
        stage4.add(Problem("북한어에서 ‘례년’은 ‘일기 예보에서, 지난 30년간의 기후의 평균적 상태를 이르는 말.’이라는 뜻이 있다.", false, "이러한 뜻은 남한어에만 해당된다.", ""))
    }
}