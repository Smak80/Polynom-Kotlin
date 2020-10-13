package ru.smak.gui.graphics.convertation

/**
 * Объект-синглон для осуществления преобразования координат в различных системах
 */
object Converter {

    /**
     * Преобразование абсциссы из декартовой системы координат в экранную
     *
     * @param x абсцисса точки в декартовой системе координат
     * @param plane плоскость, для которой осуществляется преобразование
     * @return абсцисса точки в экранной системе координат
     */
    fun xCrt2Scr(x: Double, plane: CartesianScreenPlane): Int {
        var r = (plane.xDen * (x - plane.xMin)).toInt()
        if (r < -plane.width) r = -plane.width
        if (r > 2 * plane.width) r = 2 * plane.width
        return r
    }

    /**
     * Преобразование абсциссы из экранной системы координат в декартовую
     *
     * @param x абсцисса точки в экранной системе координат
     * @param plane плоскость, для которой осуществляется преобразование
     * @return абсцисса точки в декартовой системе координат
     */
    fun xScr2Crt(x: Int, plane: CartesianScreenPlane): Double {
        var _x = x
        if (_x < -plane.width) _x = -plane.width
        if (_x > 2 * plane.width) _x = 2 * plane.width
        return _x / plane.xDen + plane.xMin
    }

    /**
     * Преобразование ординаты из декартовой системы координат в экранную
     *
     * @param y ордината точки в декартовой системе координат
     * @param plane плоскость, для которой осуществляется преобразование
     * @return ордината точки в экранной системе координат
     */
    fun yCrt2Scr(y: Double, plane: CartesianScreenPlane): Int {
        var r = (plane.yDen * (plane.yMax - y)).toInt()
        if (r < -plane.height) r = -plane.height
        if (r > 2 * plane.height + 10) r = 2 * plane.height
        return r
    }

    /**
     * Преобразование ординаты из экранной системы координат в декартовую
     *
     * @param y ордината точки в экранной системе координат
     * @param plane плоскость, для которой осуществляется преобразование
     * @return ордината точки в декартовой системе координат
     */
    fun yScr2Crt(y: Int, plane: CartesianScreenPlane): Double {
        var _y = y
        if (_y < -plane.height) _y = -plane.height
        if (_y > 2 * plane.height) _y = 2 * plane.height
        return plane.yMax - _y / plane.yDen
    }
}