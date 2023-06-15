package inc.fabudi.chronometer

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext + SupervisorJob())
    private lateinit var label: TextView

    companion object {
        var counter = 0
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        label = findViewById(R.id.label)
        scope.launch(Dispatchers.IO) {
            while (true) {
                delay(1000)
                withContext(Dispatchers.Main) {
                    label.text = (++counter).toString()
                }
            }
        }
    }
}