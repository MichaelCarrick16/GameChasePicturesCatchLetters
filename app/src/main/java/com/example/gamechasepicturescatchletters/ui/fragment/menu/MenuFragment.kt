package com.example.gamechasepicturescatchletters.ui.fragment.menu

import android.app.Dialog
import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gamechasepicturescatchletters.R
import kotlinx.android.synthetic.main.frag_menu.*

class MenuFragment : Fragment() {
    private lateinit var mediaPlayer : MediaPlayer
    private lateinit var menuAndDetailViewModel : MenuAndDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViews()
        return inflater.inflate(R.layout.frag_menu,container,false)
    }

    private fun initViews() {
        mediaPlayer  = MediaPlayer.create(requireContext(),R.raw.sound)

        menuAndDetailViewModel = ViewModelProvider(this).get(MenuAndDetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkRotateScreen()

        var navController = findNavController()

        imv_info_menu.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                handleInfoMenu()
            }
        })

        imv_sound_menu.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                handleSoundMenu()
            }
        })


        imv_play_menu.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                navController.navigate(R.id.action_menuFragment_to_detailFragment)

            }
        })

        imv_ruby_menu.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {

            }
        })
    }

    private fun checkRotateScreen() {
        var check : Boolean = menuAndDetailViewModel.resultCheckMediaPlayer
        if(check){
            imv_sound_menu.setImageResource(R.drawable.ic_sound_off_menu)
            mediaPlayer.pause()
        }else{
            imv_sound_menu.setImageResource(R.drawable.ic_sound_on_menu)
            mediaPlayer.start()
        }
    }

    private fun handleSoundMenu() {
        if(menuAndDetailViewModel.resultCheckMediaPlayer == true){
            mediaPlayer.start()
            menuAndDetailViewModel.resultCheckMediaPlayer = false
            imv_sound_menu.setImageResource(R.drawable.ic_sound_on_menu)
        }
        else{
            mediaPlayer.pause()
            menuAndDetailViewModel.resultCheckMediaPlayer = true
            imv_sound_menu.setImageResource(R.drawable.ic_sound_off_menu)
        }

    }

    private fun handleInfoMenu() {
        var customDialog = Dialog(requireContext(),android.R.style.Theme_Light)
        customDialog.setCancelable(false)
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialog.setContentView(R.layout.custom_dialog_menu)
        customDialog.getWindow()!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        var btOkMenu : Button = customDialog.findViewById(R.id.bt_ok_menu)
        btOkMenu.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }
}