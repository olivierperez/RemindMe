package fr.o80.remindme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    private val shouldGoToWork = ShouldGoToWorkUseCase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.showNotification).setOnClickListener {
            if (shouldGoToWork(Calendar.getInstance())) {
                Toast.makeText(this, getString(R.string.goToWork_message), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.stayAtHome_message), Toast.LENGTH_SHORT).show()
            }
        }
    }

}
