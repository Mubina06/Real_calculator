package com.example.calculator10th

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button
    private lateinit var zero: Button

    private lateinit var point: Button
    private lateinit var clear: Button

    private lateinit var div: Button
    private lateinit var multiply: Button
    private lateinit var plus: Button
    private lateinit var minus: Button


    private lateinit var operand: TextView
    private lateinit var result: TextView

    private var isPoint = true
    private var isSimvol = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        one.setOnClickListener(this)
        two.setOnClickListener(this)
        three.setOnClickListener(this)
        four.setOnClickListener(this)
        five.setOnClickListener(this)
        six.setOnClickListener(this)
        seven.setOnClickListener(this)
        eight.setOnClickListener(this)
        nine.setOnClickListener(this)
        zero.setOnClickListener(this)

        clear.setOnClickListener {
            operand.text = "0"
            isPoint = true
            isSimvol = true
        }

        point.setOnClickListener {
            if (isPoint) {
//                isDigit()
                operand.text = operand.text.toString() + "."
                isPoint = false
            }
        }
        div.setOnClickListener {
            addSimvol("/")
        }
        multiply.setOnClickListener {
            addSimvol("x")
        }
        plus.setOnClickListener {
            addSimvol("+")
        }
        minus.setOnClickListener {
            addSimvol("-")
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?) {
        val btn = findViewById<Button>(view!!.id)
        if (operand.text == "0") {
            operand.text = ""
        }
        operand.text = operand.text.toString() + btn.text
        isSimvol = true
        result.text = caculate()
    }

    private fun caculate(): String {
        var list = createArray(operand.text.toString())
        hisobla1(list)
        return "123"
    }

    private fun createArray(s: String): MutableList<Any> {
        var list = mutableListOf<Any>()
        var temp = ""
        for (i in s) {
            if (i.isDigit() || i == '.') {
                temp += i
            } else {
                list.add(temp.toFloat())
                temp = ""
                list.add(i)
            }
        }
        if (temp.isNotEmpty()) {
            list.add(temp.toFloat())
        }
        return list
    }

    fun hisobla1(l: MutableList<Any>): MutableList<Any> {
        var list = l
        var i = 0
        while (list.contains('/') || list.contains('x')) {

            if (list[i] == 'x' || list[i] == '/') {
                var old = list[i - 1] as Float
                var next = list[i + 1] as Float
                var amal = list[i]
                var res = 0f
                when (amal) {
                    '/' -> {
                        res = old / next
                    }
                    'x' -> {
                        res = old * next
                    }
                }
                list.set(i-1,res)
                list.removeAt(i)
                list.removeAt(i)
                i = i-2
            }
            i++
        }
        Log.d("TAG", list.toString())

        return l
    }

    @SuppressLint("SetTextI18n")
    private fun addSimvol(simvol: String) {
        if (isSimvol) {
            operand.text = operand.text.toString() + simvol
            isSimvol = false
        } else {
            operand.text = operand.text.dropLast(1).toString() + simvol
        }

    }

    fun initUI() {
        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        zero = findViewById(R.id.zero)

        point = findViewById(R.id.point)
        clear = findViewById(R.id.clear)
        div = findViewById(R.id.div)
        multiply = findViewById(R.id.multiply)
        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)


        operand = findViewById(R.id.operand)
        result = findViewById(R.id.result)
    }
}