package ru.smak.gui.components

import ru.smak.gui.graphics.Painter
import java.awt.Graphics
import javax.swing.JPanel

class GraphicsPanel : JPanel(){
    var painter: Painter? = null
    set(value) {
        field = value
        repaint()
    }

    override fun paint(g: Graphics?) {
        super.paint(g)
        painter?.paint(g)
    }

}