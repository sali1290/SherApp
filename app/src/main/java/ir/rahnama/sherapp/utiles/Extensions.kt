package ir.rahnama.sherapp.utiles

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context?.toast(message:String) = this?.let { Toast.makeText(it,message,Toast.LENGTH_LONG).show() }

fun View.setGone() { this.visibility = View.GONE }

fun View.setVisible() { this.visibility = View.VISIBLE }

fun View.setInvisible() { this.visibility  = View.INVISIBLE }
