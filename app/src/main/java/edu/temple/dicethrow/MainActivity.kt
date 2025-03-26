package edu.temple.dicethrow

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider


/*
Our DieThrow application has been refactored to move the dieRoll() logic
into the ViewModel instead of the Fragment.
Study the DieViewModel, ButtonFragment, and DieFragment classes to
see the changes.

Follow the requirements below to have this app function
in both portrait and landscape configurations.
The Activity layout files for both Portrait and Landscape are already provided
*/

class MainActivity : AppCompatActivity(), ButtonFragment.ButtonInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dieViewModel : DieViewModel by lazy {
            ViewModelProvider(this)[DieViewModel::class.java]
        }

        val isPortrait = findViewById<View>(R.id.container2) == null

        // retain state
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container1, ButtonFragment())
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit()
        }

        // if landscape
        if (!isPortrait) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container2, DieFragment())
                .replace(R.id.container1, ButtonFragment())
                .commit()
        }

        /* TODO 1: Load fragment(s)
            - Show _only_ ButtonFragment if portrait
            - show _both_ fragments if Landscape
          */

    }

    /* TODO 2: switch fragments if die rolled and in portrait (no need to switch fragments if Landscape)
        */

    // This callback function gets invoked when the child Fragment invokes it
    // Remember to place Fragment transactions on BackStack so then can be reversed
    override fun buttonClicked() {
        val isPortrait = findViewById<View>(R.id.container2) == null

        if (isPortrait) {
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container1, DieFragment())
                .commit()
        }
    }


}