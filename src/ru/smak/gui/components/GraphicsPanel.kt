package ru.smak.gui.components

import ru.smak.gui.graphics.Painter
import java.awt.Graphics
import javax.swing.JPanel

class GraphicsPanel(private val painter: Painter) : JPanel(){
    override fun paint(g: Graphics?) {
        super.paint(g)
        painter.paint(g)
        //this.graphics
    }
}