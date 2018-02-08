package eugene.com.kotlininro.util

import android.graphics.Color
import android.support.annotation.ColorInt

object ColorUtil {

    fun getColors(colorPrimary: String): IntArray {
        val primary = Color.parseColor(colorPrimary)
        val colors = IntArray(4)
        colors[0] = primary
        colors[1] = if (colorPrimary == "#FFFFFF") Color.parseColor("#CCCCCC") else colorPrimaryDark(primary)
        colors[2] = getContrastColor(primary)
        colors[3] = if (colorPrimary == "#FFFFFF") Color.parseColor("#000000") else colors[1]
        return colors
    }

    fun colorPrimaryDark(colorPrimary: Int): Int {
        var hsv = FloatArray(3)
        Color.colorToHSV(colorPrimary, hsv)
        val hsl = hsv2hsl(hsv)
        hsl[2] -= 12 / 100f
        if (hsl[2] < 0)
            hsl[2] = 0f
        hsv = hsl2hsv(hsl)
        return Color.HSVToColor(hsv)
    }

    @ColorInt
    private fun getContrastColor(@ColorInt color: Int): Int {
        // Counting the perceptive luminance - human eye favors green color...
        val a = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
        return if (a < 0.5) Color.BLACK else Color.WHITE
    }

    /**
     * Converts HSV (Hue, Saturation, Value) color to HSL (Hue, Saturation, Lightness)
     * Credit goes to xpansive
     * https://gist.github.com/xpansive/1337890
     *
     * @param hsv HSV color array
     * @return hsl
     */
    private fun hsv2hsl(hsv: FloatArray): FloatArray {
        val hue = hsv[0]
        val sat = hsv[1]
        val `val` = hsv[2]

        //Saturation is very different between the two color spaces
        //If (2-sat)*val < 1 set it to sat*val/((2-sat)*val)
        //Otherwise sat*val/(2-(2-sat)*val)
        //Conditional is not operating with hue, it is reassigned!
        // sat*val/((hue=(2-sat)*val)<1?hue:2-hue)
        val nhue = (2f - sat) * `val`
        var nsat = sat * `val` / if (nhue < 1f) nhue else 2f - nhue
        if (nsat > 1f)
            nsat = 1f

        return floatArrayOf(
                //[hue, saturation, lightness]
                //Range should be between 0 - 1
                hue, //Hue stays the same

                // check nhue and nsat logic
                nsat,

                nhue / 2f //Lightness is (2-sat)*val/2
        )//See reassignment of hue above
    }

    /**
     * Reverses hsv2hsl
     * Credit goes to xpansive
     * https://gist.github.com/xpansive/1337890
     *
     * @param hsl HSL color array
     * @return hsv color array
     */
    private fun hsl2hsv(hsl: FloatArray): FloatArray {
        val hue = hsl[0]
        var sat = hsl[1]
        val light = hsl[2]

        sat *= if (light < .5) light else 1 - light

        return floatArrayOf(
                //[hue, saturation, value]
                //Range should be between 0 - 1

                hue, //Hue stays the same
                2f * sat / (light + sat), //Saturation
                light + sat //Value
        )
    }
}
