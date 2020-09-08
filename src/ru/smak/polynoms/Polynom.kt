package ru.smak.polynoms

import kotlin.math.abs
import kotlin.math.max

class Polynom(coef: DoubleArray) {

    /**
     * Коэффициенты полинома
     */
    var coef: DoubleArray = coef.clone()
    get() = coef.clone() //Возвращаем копию коэффициентов полинома
    private set //и запрещаем изменения массива извне

    private val ZERO = 0.0 //Изменить

    /**
     * Степень полинома
     */
    val power : Int
    get() = coef.size - 1


    init{
        correctPower()
    }

    /**
     * Вторичный конструктор для создания полинома нулевой степени = 0
     */
    constructor() : this(doubleArrayOf(0.0))

    /**
     * Удаление нулевых коэффициентов при старших степенях
     */
    private fun correctPower(){
        var b = true
        coef.reversed().filterIndexed { i, v ->
            if (b && abs(v) > ZERO ) b = false
            !(b && i>0)
        }.reversed().toDoubleArray()
    }

    /**
     * Сложение двух полиномов
     * @param other второй (правый) полином
     * @return полином, являющийся результатом суммирования данного полинома с другим
     */
    operator fun plus(other: Polynom) =
        Polynom( DoubleArray(max(power, other.power) + 1)
            {
            (if (it<coef.size) coef[it] else 0.0) +
                    (if (it<other.coef[it]) other.coef[it] else 0.0)
            }
        )

    /**
     * Определение значения произведения полинома на число
     * @param k вещественный коэффициент
     * @return результат умножения данного (this) полинома на число
     */
    operator fun times(k: Double) =
        Polynom(DoubleArray(power+1){ coef[it] * k })

    /**
     * Определение разности двух полиномов
     * @param other вычитаемый полином
     * @return разность данного (this) и второго (other) полиномов
     */
    operator fun minus(other: Polynom) =
        this + other * -1.0

    override fun toString(): String {
        return super.toString()
    }
}