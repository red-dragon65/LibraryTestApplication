package com.example.supercalc

import android.util.Log
import com.example.supercalc.Strategies.Addition
import com.example.supercalc.Strategies.CalcStrat

class CustomCalculator(calculationStrategy: CalcStrat) : CalcStrat {

    // final
    private var userCalcStrat: CalcStrat = calculationStrategy
    private var userSet = true

    // Use default addition
    constructor(): this(Addition()){
        userSet = false
    }

    override fun gigaCalc(number: List<Int>): Int {

        if(!userSet){
            Log.w("FROM: " + this.javaClass, "CALC STRAT NOT DEFINED! Using default 'addition' strategy instead.")
        }

        return userCalcStrat.gigaCalc(number)
    }

    fun swapStrat(newStrat: CalcStrat){

        userCalcStrat = newStrat
    }
}