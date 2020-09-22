package ru.smak.components

import javax.swing.*
import javax.swing.border.EtchedBorder

class LagrangeControl : JPanel(){

    private val lXMin: JLabel
    private val lXMax: JLabel
    private val lYMin: JLabel
    private val lYMax: JLabel

    private val sXMin: JSpinner
    private val sXMax: JSpinner
    private val sYMin: JSpinner
    private val sYMax: JSpinner

    private val smXMin: SpinnerNumberModel
    private val smXMax: SpinnerNumberModel
    private val smYMin: SpinnerNumberModel
    private val smYMax: SpinnerNumberModel

    companion object{
        private val MIN_SZ = GroupLayout.PREFERRED_SIZE
        private val MAX_SZ = GroupLayout.DEFAULT_SIZE
    }

    init{
        border = EtchedBorder()

        lXMin = JLabel()
        lXMax = JLabel()
        lYMin = JLabel()
        lYMax = JLabel()
        lXMin.text = "Xmin:"
        lXMax.text = "Xmax:"
        lYMin.text = "Ymin:"
        lYMax.text = "Ymax:"

        smXMin = SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1)
        smXMax = SpinnerNumberModel(5.0, -4.9, 100.0, 0.1)
        smYMin = SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1)
        smYMax = SpinnerNumberModel(5.0, -4.9, 100.0, 0.1)

        sXMin = JSpinner(smXMin)
        sXMax = JSpinner(smXMax)
        sYMin = JSpinner(smYMin)
        sYMax = JSpinner(smYMax)

        val gl = GroupLayout(this)
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(lXMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(sXMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(lXMax, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(sXMax, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(8)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(lYMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(sYMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(lYMax, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(sYMax, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(4)
        )

        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                        .addGap(4)
                        .addGroup(
                                gl.createParallelGroup()
                                        .addComponent(lXMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addComponent(lYMin, MIN_SZ, MIN_SZ, MIN_SZ)
                        )
                        .addGap(2)
                        .addGroup(
                                gl.createParallelGroup()
                                        .addComponent(sXMin, MIN_SZ, 100, MAX_SZ)
                                        .addComponent(sYMin, MIN_SZ, 100, MAX_SZ)
                        )
                        .addGap(8, 8, Int.MAX_VALUE)
                        .addGroup(
                                gl.createParallelGroup()
                                        .addComponent(lXMax, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addComponent(lYMax, MIN_SZ, MIN_SZ, MIN_SZ)
                        )
                        .addGap(2)
                        .addGroup(
                                gl.createParallelGroup()
                                        .addComponent(sXMax, MIN_SZ, 100, MAX_SZ)
                                        .addComponent(sYMax, MIN_SZ, 100, MAX_SZ)
                        )
                        .addGap(4)
        )
        layout = gl
    }
}