package com.example.my_tic_tac_toe

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.my_tic_tac_toe.R.id.poga
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    inner class laukuma_dala(val rindas: Int, val kollon:Int)

    fun ButtonClick(view: View) {


        var poga: Button = findViewById(R.id.poga)
        poga.setOnClickListener{
            Toast.makeText(this@MainActivity,"Izbaudiet speli!", Toast.LENGTH_SHORT).show()
        }
    }

    var aC = Array(3) { arrayOfNulls<ImageView>(3) }
    private lateinit var button:Button
    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onlauk()
        res.setOnClickListener{
            laukum = laukums()
            text1.text = ""
            link()
        }


    }
    fun onlauk() {

        for (rindas in aC.indices) {
            for (kollon in aC.indices) {
                aC[rindas][kollon] = ImageView(this)
                aC[rindas][kollon]?.layoutParams = GridLayout.LayoutParams().apply {
                    topMargin = 3
                    leftMargin = 3
                    rowSpec = GridLayout.spec(rindas)
                    columnSpec = GridLayout.spec(kollon)
                    width = 250
                    height = 250

                }
                aC[rindas][kollon]?.setBackgroundColor(ContextCompat.getColor(this, R.color.orange))
                aC[rindas][kollon]?.setOnClickListener(ChekClick(rindas,kollon))
                addlay.addView(aC[rindas][kollon])
            }
        }
    }


    var laukum = laukums()

    fun link(){
        for (rindas in laukum.lokacija.indices){
            for (kollon in laukum.lokacija.indices ){
                when (laukum.lokacija[rindas][kollon]){
                    "0" ->{
                    aC[rindas][kollon]?.setImageResource(R.drawable.nulle)
                        aC[rindas][kollon]?.isEnabled = false
                    }
                    "X"->{
                        aC[rindas][kollon]?.setImageResource(R.drawable.krustini)
                        aC[rindas][kollon]?.isEnabled = false
                    }
                    else ->{
                        aC[rindas][kollon]?.setImageResource(0)
                        aC[rindas][kollon]?.isEnabled = true
                    }
                }
            }

        }
    }




    inner class ChekClick(var rindas: Int, var kollon: Int)
         : View.OnClickListener {

        override fun onClick(p0: View?) {
            if(!laukum.end){
                var cell = laukuma_dala(rindas,kollon)
                laukum.gajiens(cell,"0")

                if(laukum.nullloc.isNotEmpty()) {
                    var cCell = laukum.nullloc[Random.nextInt(0, laukum.nullloc.size)]
                    laukum.gajiens(cCell,"X")
                }
                link()
            }
            when {

                laukum.hasComputerWon() -> text1.text = "Dators uzvareja"
                laukum.hasPlayerWon() -> text1.text = "Tu uzvareji"
                laukum.end -> text1.text = "Neizskirts"
            }
        }

    }

    inner class laukums {
        var lokacija = Array(3) { arrayOfNulls<String>(3) }
        val nullloc: List<laukuma_dala>
            get() {
                var cells = mutableListOf<laukuma_dala>()
                for (rindas in lokacija.indices) {
                    for (kollon in lokacija.indices) {
                        if (lokacija[rindas][kollon].isNullOrEmpty()) {
                            cells.add(laukuma_dala(rindas, kollon))
                        }
                    }
                }
                return cells
            }


        val end: Boolean
            get() = hasComputerWon() || hasPlayerWon() || nullloc.isEmpty()



        fun hasComputerWon(): Boolean {
            if (lokacija[0][0] == lokacija[1][1] && lokacija[0][0] == lokacija[2][2] && lokacija[0][0] == "X" || lokacija[0][2] == lokacija[1][1] && lokacija[0][2] == lokacija[2][0] && lokacija[0][2] == "X"
            ) {
                return true
            }

            for (rindas in lokacija.indices) {
                if (lokacija[rindas][0] == lokacija[rindas][1] && lokacija[rindas][0] == lokacija[rindas][2] && lokacija[rindas][0] == "X" || lokacija[0][rindas] == lokacija[1][rindas] && lokacija[0][rindas] == lokacija[2][rindas] && lokacija[0][rindas] == "X"
                ) {
                    return true
                }
            }

            return false
        }

        fun hasPlayerWon(): Boolean {

            if (lokacija[0][0] == lokacija[1][1] && lokacija[0][0] == lokacija[2][2] && lokacija[0][0] == "0" || lokacija[0][2] == lokacija[1][1] && lokacija[0][2] == lokacija[2][0] && lokacija[0][2] == "0"
            ) {
                return true
            }

            for (rindas in lokacija.indices) {
                if (lokacija[rindas][0] == lokacija[rindas][1] && lokacija[rindas][0] == lokacija[rindas][2] && lokacija[rindas][0] == "0" || lokacija[0][rindas] == lokacija[1][rindas] && lokacija[0][rindas] == lokacija[2][rindas] && lokacija[0][rindas] == "0"
                ) {
                    return true
                }
            }

            return false
        }
        fun gajiens(cell: laukuma_dala, player: String) {
            lokacija[cell.rindas][cell.kollon] = player
        }

    }



    fun Click(view: View) {
        var name:EditText=findViewById(R.id.name)
        var getNam:TextView=findViewById(R.id.text1)

        var str:String=name.text.toString()
        getNam.setText(str)
    }


}