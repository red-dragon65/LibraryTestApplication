package com.example.supercalc.Strategies

class Addition : CalcStrat {

    override fun gigaCalc(number: List<Int>): Int {

        var result = 0

        for(i in number){

            result += i
        }

        return result
    }
}