package jp.techacademy.hiroki.eguchi.calcapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import jp.techacademy.hiroki.eguchi.calcapp.databinding.ActivityMainBinding

//4つの四則計算ボタンのどれかをタップすると、2つ目の画面へ移動させてTextViewで対応する計算結果を表示してください
//数値は小数点に対応してください(int型→Double型に変換して表示すること)
//EditTextに何も値が入っていない場合、四則計算ボタンをタップしてもエラーが起きないようにしてください。(nullチェックと数値のバリデーションチェック)

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button1.setOnClickListener(this)
        binding.button2.setOnClickListener(this)
        binding.button3.setOnClickListener(this)
        binding.button4.setOnClickListener(this)
    }

    public fun showAlertDialog() {
        // AlertDialog.Builderクラスを使ってAlertDialogの準備をする
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("バリデーションチェックエラー")
        alertDialogBuilder.setMessage("ExitTextの設定値を見直しください。")

        Log.d("UI_PARTS", "Hello Dialog")
        // 肯定ボタンに表示される文字列、押したときのリスナーを設定する
        alertDialogBuilder.setPositiveButton("肯定"){dialog, which ->
            Log.d("UI_PARTS", "肯定ボタン")
        }

        // 中立ボタンに表示される文字列、押したときのリスナーを設定する
        // 使わない引数の場合は「_」と記述するのがkotlinの慣習
        alertDialogBuilder.setNeutralButton("中立"){_,_ ->
            Log.d("UI_PARTS", "中立ボタン")
        }

        // 否定ボタンに表示される文字列、押したときのリスナーを設定する
        alertDialogBuilder.setNegativeButton("否定"){_,_ ->
            Log.d("UI_PARTS", "否定ボタン")
        }

        // AlertDialogを作成して表示する
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onClick(v: View?) {
        // ExitText1、ExitText2のnullチェック
        if(binding.editText1.toString().toDoubleOrNull() != null && binding.editText2.toString().toDoubleOrNull() != null) {
            showAlertDialog()
        }else {
            val intent = Intent(this, SecondActivity::class.java)
            val value1 = binding.editText1.toString().toDouble()
            Log.d("UI_PARTS", "aaa")
            val value2 = binding.editText2.toString().toDouble()
            Log.d("UI_PARTS", "bbb")
            if (v != null) {
                if (v.id == R.id.button1) {
                    intent.putExtra("VALUE1", "${value1 + value2}");
                } else if (v.id == R.id.button2) {
                    intent.putExtra("VALUE1", "${value1 - value2}");
                } else if (v.id == R.id.button3) {
                    intent.putExtra("VALUE1", "${value1 * value2}");
                } else if (v.id == R.id.button4) {
                    if (value2 != 0.0) {
                        intent.putExtra("VALUE1", "${value1 / value2}")
                    } else {
                        intent.putExtra("VALUE1", "${value1}を0で割ることはできません。")
                    }
                } else {
                    Log.d("UI_PARTS", "ccc")
                    showAlertDialog()
                }
            startActivity(intent)
            } else {
                showAlertDialog()
            }
        }
    }
}