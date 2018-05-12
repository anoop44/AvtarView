package ss.anoop.avtarview

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.widget.ImageView
import kotlin.math.min


class AvtarView(context: Context, attributeSet: AttributeSet?, defStyle: Int) : ImageView(context, attributeSet, defStyle) {

    companion object {
        const val DEFAULT_DIMEN = 30
        const val DEFAULT_TEXT_SIZE = 14
        const val DEFAULT_STROKE_THICKNESS = 0
        const val DEFAULT_TEXT_COLOR = Color.WHITE
        const val DEFAULT_STROKE_COLOR = Color.WHITE
        val AVTAR_COLOR_LIST = arrayOf("#f44336", "#E91E63", "#9C27B0", "#673AB7", "#3F51B5", "#2196F3", "#03A9F4", "#00BCD4", "#009688", "#4CAF50", "#8BC34A", "#CDDC39", "#FFEB3B", "#FFC107", "##FF9800", "#FF5722",
                "#b71c1c", "#880E4F", "#4A148C", "#311B92", "#1A237E", "#0D47A1", "#01579B", "#006064", "#004D40", "#1B5E20", "#33691E", "#F57F17", "#FF6F00", "#E65100", "#BF360C", "#3E2723", "#212121", "#263238")
        var DEFAULT_AVTAR_COLOR = Color.parseColor("#295db4")
    }

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context) : this(context, null)

    //For drawing the text
    private val textPaint: TextPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)

    //For drawing the circle in case image is not set
    private val circlePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    //For drawing the stroke in case stroke width is set to more than 0
    private val strokePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    //Defines a circular path to clip the canvas as a circle
    private val path = Path()

    private var radius = 0f

    private var textToDraw: String? = null

    private var rect = Rect()

    init {
        circlePaint.color = DEFAULT_AVTAR_COLOR

        strokePaint.style = Paint.Style.STROKE

        //just to see something in the preview
        setText("A")

        attributeSet?.let { init(it) }
    }

    private fun init(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AvtarView, 0, 0)

        circlePaint.color = typedArray.getColor(R.styleable.AvtarView_avatarColor, DEFAULT_AVTAR_COLOR)

        textPaint.color = typedArray.getColor(R.styleable.AvtarView_textColor, DEFAULT_TEXT_COLOR)
        textPaint.textSize = typedArray.getDimension(R.styleable.AvtarView_textSize, spToPx(DEFAULT_TEXT_SIZE))

        strokePaint.strokeWidth = typedArray.getDimension(R.styleable.AvtarView_strokeWidth, dpToPx(DEFAULT_STROKE_THICKNESS))
        strokePaint.color = typedArray.getColor(R.styleable.AvtarView_strokeColor, DEFAULT_STROKE_COLOR)

        setText(typedArray.getString(R.styleable.AvtarView_text))
    }

    fun setTextSize(textSize: Float) {
        textPaint.textSize = textSize
        invalidate()
    }

    fun setTextColor(color: Int) {
        textPaint.color = color
        invalidate()
    }

    fun setTextFont(typeface: Typeface) {
        textPaint.typeface = typeface
        invalidate()
    }

    fun setStrokeWidth(strokeWidth: Float) {
        strokePaint.strokeWidth = strokeWidth
        invalidate()
    }

    fun setStrokeColor(strokeColor: Int) {
        strokePaint.color = strokeColor
        invalidate()
    }

    /**
     * The initial is extracted from the string passed. Also the entire string is used to assign color to Avtar unless a color is set explicitly
     *
     * @param text for which the Avtar has to be generator.
     */
    fun setText(text: String?) {
        text?.let {
            if (!it.isBlank()) {
                textToDraw = it.toCharArray()[0].toString()
                if(circlePaint.color == DEFAULT_AVTAR_COLOR){
                    var sum = 0
                    it.toCharArray().forEach { char -> sum+= char.toInt() }
                    circlePaint.color = Color.parseColor(AVTAR_COLOR_LIST[sum % AVTAR_COLOR_LIST.size])
                }
                invalidate()
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val dimension = if ((MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.UNSPECIFIED || MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST)
                && (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED || MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST)) {
            dpToPx(DEFAULT_DIMEN).toInt()
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            min(measuredWidth, measuredHeight)
        }

        radius = dimension / 2f
        path.addCircle(radius, radius, radius, Path.Direction.CW)

        setMeasuredDimension(dimension, dimension)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.clipPath(path)
        if (null != drawable) {
            super.onDraw(canvas)
        } else {
            canvas?.drawCircle(radius, radius, radius, circlePaint)
            if (!textToDraw.isNullOrBlank()) {

                textPaint.getTextBounds(textToDraw, 0, 1, rect)

                canvas?.drawText(textToDraw, radius - rect.width() / 2, radius + rect.height() / 2, textPaint)
            }
        }
        if (strokePaint.strokeWidth > 0) {
            canvas?.drawCircle(radius, radius, radius - strokePaint.strokeWidth / 2f, strokePaint)
        }

    }

    private fun dpToPx(dp: Int) = dp * context.resources.displayMetrics.density

    private fun spToPx(sp: Int) = sp * context.resources.displayMetrics.scaledDensity

}