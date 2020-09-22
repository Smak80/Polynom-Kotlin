package ru.smak.polynoms

class Lagrange(xfx: MutableMap<Double, Double>) : Polynom(){

    val xfx = xfx.toMutableMap()
    init{
        val p = Polynom()
        xfx.forEach {
            val r = fundamental(it.key)
            if (r != null) p += r * it.value
            else return@forEach
        }
        coef = p.coefficients
    }

    /**
     * Вычисление фундаментальных полиномов Лагранжа
     */
    private fun fundamental(key: Double): Polynom? {
        var p: Polynom = Polynom(doubleArrayOf(1.0))
        xfx.forEach {
            if (it.key.compareTo(key)!=0){
                val m = Polynom(doubleArrayOf(-it.key, 1.0)) /
                        (key - it.key)
                if (m != null){
                    p *= m
                } else return@fundamental null
            }
        }
        return p
    }

}