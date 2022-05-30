package com.example.medicsapp.room.database

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.medicsapp.base.setup.BaseSetupForNotification
import com.example.medicsapp.databinding.CustomAlertMessagesBinding

class AlertExtension {

    companion object {
        fun showDialogue(context: Context, title: String, description: String, obj: (Boolean) -> Unit) {
            val bind: CustomAlertMessagesBinding = CustomAlertMessagesBinding.inflate(
                (context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE
                ) as LayoutInflater)
            )
            val alertDialogueBuilder = AlertDialog.Builder(context)
            alertDialogueBuilder.setView(bind.root)
            bind.alertTitle.text = title
            bind.alertDescreption.text = description
            val dialog = alertDialogueBuilder.create()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
            bind.btnLogin.setOnClickListener {
                obj(true)
            }
        }
    }

}