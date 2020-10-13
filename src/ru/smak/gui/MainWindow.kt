package ru.smak.gui

import ru.smak.gui.components.ControlPanel
import ru.smak.gui.components.GraphicsPanel
import ru.smak.gui.graphics.CartesianPainter
import ru.smak.gui.graphics.convertation.CartesianScreenPlane
import java.awt.Color
import java.awt.Dimension
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.ComponentListener
import javax.swing.GroupLayout
import javax.swing.JFrame

class MainWindow : JFrame(){

    private val minSize = Dimension(550, 400)
    private val mainPanel: GraphicsPanel
    private val controlPanel: ControlPanel
    init{
        defaultCloseOperation = EXIT_ON_CLOSE
        minimumSize = Dimension(minSize.width+200, minSize.height+400)
        mainPanel = GraphicsPanel()
        mainPanel.background = Color.WHITE
        controlPanel = ControlPanel()
        val gl = GroupLayout(contentPane)

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addComponent(mainPanel, minSize.height, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(4)
                .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(4)
        )

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(mainPanel, minSize.width, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(4))
        layout = gl
        pack()
        val plane = CartesianScreenPlane(
                mainPanel.width, mainPanel.height,
                controlPanel.smXMin.number.toDouble(),
                controlPanel.smXMax.number.toDouble(),
                controlPanel.smYMin.number.toDouble(),
                controlPanel.smYMax.number.toDouble()
        )
        val dp = CartesianPainter(plane)
        mainPanel.addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                dp.plane.realWidth = mainPanel.width
                dp.plane.realHeight = mainPanel.height
            }
        })
        controlPanel.addValChangeListener {
            dp.plane.xMin = controlPanel.smXMin.number.toDouble()
            dp.plane.xMax = controlPanel.smXMax.number.toDouble()
            dp.plane.yMin = controlPanel.smYMin.number.toDouble()
            dp.plane.yMax = controlPanel.smYMax.number.toDouble()
            mainPanel.repaint()
        }
        mainPanel.painter = dp
    }
}