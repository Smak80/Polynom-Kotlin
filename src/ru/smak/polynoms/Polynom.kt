package ru.smak.polynoms

import kotlin.math.abs
import kotlin.math.max

class Polynom(coef: DoubleArray) {

    /**
     * Коэффициенты полинома для использования внутри класса
     */
    private var coef: DoubleArray = coef.clone()

    /**
     * Свойство для доступа к коэффициентам полинома извне
     */
    val coefficients: DoubleArray
        get() = coef.clone() //Возвращаем копию коэффициентов полинома

    /**
     * Степень полинома
     */
    val power : Int
    get() = coef.size - 1

    /**
     * Тело первичного конструктора
     */
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
    private fun correctPower() {
        var b = true
        coef = coef.reversed().filterIndexed { i, v ->
            if (v.compareTo(0.0) != 0) b = false
            !b || (i == power)
        }.reversed().toDoubleArray()
    }

    /**
     * Сложение двух полиномов
     * @param other второй (правый) полином
     * @return полином, являющийся результатом суммирования данного полинома с другим
     */
    operator fun plus(other: Polynom) =
        Polynom(DoubleArray(max(power, other.power) + 1)
        {
            (if (it < coef.size) coef[it] else 0.0) +
                    (if (it < other.coef[it]) other.coef[it] else 0.0)
        }
        )

    /**
     * Определение значения произведения полинома на число
     * @param k вещественный коэффициент
     * @return результат умножения данного (this) полинома на число
     */
    operator fun times(k: Double) =
        Polynom(DoubleArray(power + 1) { coef[it] * k })

    /**
     * Определение разности двух полиномов
     * @param other вычитаемый полином
     * @return разность данного (this) и второго (other) полиномов
     */
    operator fun minus(other: Polynom) =
        this + other * -1.0

    /**
     * @param other полином, на который производится умножение
     * @return произведение двух полиномов
     */
    operator fun times(other: Polynom): Polynom{
        //Создание массива коэффициентов нового полинома
        val t = DoubleArray(power + other.power + 1){ 0.0 }
        //Для каждого коэффициента первого полинома и
        coef.forEachIndexed { ti, tc ->
            //коэффициента второго полинома
            other.coef.forEachIndexed{ oi, oc ->
                t[ti + oi] += tc * oc
            }
        }
        // Создание нового полинома по рассчитанным коэффициентам
        return Polynom(t)
    }

    /**
     * Деление полинома на число
     * @param k число, на которое требуется подилить полином
     * @return частное от деления полинома на число
     */
    operator fun div(k: Double) : Polynom? =
       if (k.compareTo(0.0)!=0)
           this*(1.0/k)
       else
           null

    /**
     * Переопределение функции формирования строки с записью полинома
     * @return Строка с представлением полинома
     */
    override fun toString(): String {
        //Построитель строки, где будет формироваться результат
        val res = StringBuilder()
        //Степень полинома
        val pow = power
        //Вложенная функция, проверяющая, является ли число целым
        fun isLong(x: Double) = abs(x-x.toLong()).compareTo(0.0) == 0
        //Для каждого коэффициента в массиве...
        coef.reversed().forEachIndexed { ind, v ->
            //находим степень
            val i = pow - ind
            if (v.compareTo(0.0) != 0 || pow == 0) {
                //Если это ненулевой коэффициент или степень полинома = 0...
                //...выводим "+" или "-" в зависимости от знака коэффициента
                //("+" выводится, только если работаем не со страшей степенью
                res.append(if (v < 0) "-" else if (i < pow) "+" else "")
                //получаем модуль коэффициента для дальнейшей работы
                val c = abs(v)
                //Если коэффициент по модулю не равен 1 или это коэфф. при 0й степени
                if (c.compareTo(1.0) != 0 || i == 0)
                    //добавляем коэффициент в вывод
                    res.append(if (isLong(c)) c.toLong() else c)
                //для всех степеней, кроме нулевой
                if (i >= 1) {
                    //выводим символ 'x'
                    res.append('x')
                    //и для всех степеней, больше 1, выводим ее номер
                    if (i > 1) {
                        res.append("^$i")
                    }
                }
            }
        }
        //Возвращаем полученный результат, приведенный к строке
        return res.toString()
    }

    /**
     * Вычисление значения полинома в точке
     * @param x точка, в которой нужно вычислить значение полинома
     * @return значение полинома в точке
     */
    operator fun invoke(x: Double): Double{
        var pow = 1.0
        return coef.reduce { acc, d ->
            pow *= x; acc + d * pow
        }
    }

}

