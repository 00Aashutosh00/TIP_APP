package com.example.tippy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
private const val Tag = "MainActivity"
private const val INITIAL_TIP_PERCENT  = 15
class MainActivity : AppCompatActivity() {

    private lateinit var etBaseAmount : EditText
    private lateinit var seekBarTip : SeekBar
    private lateinit var tvTipPercentageLabel : TextView
    private lateinit var tvTipAmount : TextView
    private lateinit var tvTotalAmount : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etBaseAmount = findViewById(R.id.etBaseamount)
        seekBarTip = findViewById(R.id.seekBarTip)
        tvTipPercentageLabel = findViewById(R.id.tvTipPercentage)
        tvTipAmount = findViewById(R.id.tvTipAmount)
        tvTotalAmount = findViewById(R.id.tvTotalAmount)

        seekBarTip.progress = INITIAL_TIP_PERCENT
        tvTipPercentageLabel.text = "$INITIAL_TIP_PERCENT%"


        seekBarTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                Log.i(Tag , "onProgressChanged $progress")
                   tvTipPercentageLabel.text = "$progress%"
                  computeTipAndTotal()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        }
        )
     etBaseAmount.addTextChangedListener(object : TextWatcher{
         override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

         override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

         override fun afterTextChanged(s: Editable?) {

             Log.i(Tag , "aftertextChanged $s")
             computeTipAndTotal()
         }

     })

    }

    private fun computeTipAndTotal() {
        //1.get the value of the base and tip percent
       if(etBaseAmount.text.isEmpty()){

           tvTipAmount.text = ""
           tvTotalAmount.text = ""
       }
      val baseAmount  = etBaseAmount.text.toString().toDouble()
        val tipPercent = seekBarTip.progress

        // 2. compute the tip and total

       val tipAmount = baseAmount * tipPercent / 100
        val totalAmount = baseAmount + tipAmount
        // 3.Update the UI

        tvTipAmount.text = "%.2f".format(tipAmount.toString())
        tvTotalAmount.text = "%.2f".format(totalAmount.toString())
    }
}