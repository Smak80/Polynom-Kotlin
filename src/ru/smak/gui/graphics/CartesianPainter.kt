package ru.smak.gui.graphics

import ru.smak.gui.graphics.convertation.CartesianScreenPlane
import ru.smak.gui.graphics.convertation.Converter
import java.awt.Color
import java.awt.Graphics

class CartesianPainter(val plane: CartesianScreenPlane) : Painter{
    override fun paint(g: Graphics?) {
        drawAxes(g)
    }

    private fun drawAxes(g: Graphics?) {
        if (g!=null) {
            g.color = Color.BLACK
            g.drawLine(0,
                    Converter.yCrt2Scr(0.0, plane),
                    plane.width,
                    Converter.yCrt2Scr(0.0, plane)
            )
            g.drawLine(
                    Converter.xCrt2Scr(0.0, plane),
                    0,
                    Converter.xCrt2Scr(0.0, plane),
                    plane.height
            )
        }
    }

}