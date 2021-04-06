package ir.rahnama.sherapp.utiles

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.res.Configuration
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.PopupWindow
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.orhanobut.hawk.Hawk
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.SubscriptionFragmentBinding
import ir.rahnama.sherapp.di.AppModule
import ir.rahnama.sherapp.model.remote.VerficationCodeApiClient1
import kotlinx.android.synthetic.main.fragment_logup.view.*
import kotlinx.android.synthetic.main.subscription_fragment.view.*
import kotlinx.coroutines.Dispatchers.Unconfined
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("UseCompatLoadingForDrawables")
fun checkUser(context: Context) {
    if (Hawk.get("logIn", 0) == 0) {
        var validationCodeChecker = false
        var usernameChcker = false
        var phoneNumbeChecker = false
        var username : String = ""
        var phoneNumber  :String = ""
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_logup, null)
        val popupWindow = PopupWindow(
            view,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        popupWindow.isFocusable = true
        val configuration: Configuration = context.resources.configuration
        popupWindow.showAsDropDown(view, Gravity.CENTER, 0, 0)
        view.logIn_TextView.setInvisible()
        view.closeBtn_LogInPopUp.setOnClickListener {
            popupWindow.dismiss()
        }
        view.popUp_LogIn_TextView.setOnClickListener {
            view.verification_edt_input.text?.let { it.clear() }
            view.user_name_edt_input.text?.let { it.clear() }
            view.phone_number_edt_input.text?.let { it.clear() }
            view.phone_number_input_layout.isErrorEnabled = false
            view.verification_input_layout.isErrorEnabled = false
            view.logUp_TextView.setTextColor(context.resources.getColor(R.color.light_gray))
            view.popUp_LogIn_TextView.setTextColor(context.resources.getColor(R.color.profile_green))
            view.verification_input_layout.setInvisible()
            view.user_name_input_layout.setInvisible()
            view.user_name_edt_input.setInvisible()
            ObjectAnimator.ofFloat(
                view.phone_number_input_layout, "translationY",
                -50f
            ).apply {
                duration = 800
                start()
            }
        }
        view.logUp_TextView.setOnClickListener {
            view.verification_edt_input.text?.let { it.clear() }
            view.user_name_edt_input.text?.let { it.clear() }
            view.phone_number_edt_input.text?.let { it.clear() }
            view.phone_number_input_layout.isErrorEnabled = false
            view.verification_input_layout.isErrorEnabled = false
            view.user_name_input_layout.isErrorEnabled = false
            view.popUp_LogIn_TextView.setTextColor(context.resources.getColor(R.color.light_gray))
            view.logUp_TextView.setTextColor(context.resources.getColor(R.color.profile_green))
            view.verification_input_layout.setInvisible()
            ObjectAnimator.ofFloat(
                view.phone_number_input_layout, "translationY",
                10f
            ).apply {
                duration = 800
                start()
            }
            Log.i("tag", "afterAnimation")
            view.user_name_input_layout.setVisible()
            view.user_name_edt_input.setVisible()
            Log.i("tag", "aftervisible")
        }
        view.confirmBtn_LogInUp.setOnClickListener {
            if( phoneNumbeChecker && usernameChcker){
                username = view.user_name_edt_input.text.toString()
                phoneNumber = view.phone_number_edt_input.text.toString()
                requestToSendValidationCode(view)
                phoneNumbeChecker = false
                usernameChcker =false
            }

            if(validationCodeChecker){
                verifyValidationCode(phoneNumber , view.verification_edt_input.text.toString() , username , popupWindow)
            }


        }
        view.user_name_edt_input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                view.user_name_input_layout.isErrorEnabled = false
            }

            override fun afterTextChanged(s: Editable?) {
                if (view.user_name_edt_input.text.toString().isEmpty()) {
                    view.user_name_input_layout.isErrorEnabled = true
                    view.user_name_input_layout.error = "الزامی"
                    usernameChcker = false
                } else {
                    usernameChcker = true
                    view.user_name_input_layout.isErrorEnabled = false
                }
            }
        })
        view.phone_number_edt_input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                view.phone_number_input_layout.isErrorEnabled = false
            }
            override fun afterTextChanged(s: Editable?) {
                if (view.phone_number_edt_input.text!!.length < 11) {
                    view.phone_number_input_layout.isErrorEnabled = true
                    view.phone_number_input_layout.error = "نامعتبر"
                    phoneNumbeChecker = false
                } else {
                    view.phone_number_input_layout.isErrorEnabled = true
                    phoneNumbeChecker = true
                }
            }

        })
        view.verification_edt_input.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                view.verification_input_layout.isErrorEnabled = true
            }
            override fun afterTextChanged(s: Editable?) {
                if(view.verification_edt_input.text!!.length < 5 ){
                    view.verification_input_layout.isErrorEnabled = true
                    view.verification_input_layout.error = "نامعتبر"
                    validationCodeChecker  = false
                } else {
                    validationCodeChecker = true
                    view.verification_input_layout.isErrorEnabled =false
                }
            }

        })
    }
}


 fun verifyValidationCode(userPhone: String, Code: String, userName: String, popupWindow: PopupWindow) {


     val verifyCodeApi: VerficationCodeApiClient1 =
        AppModule.provideRetrofit(AppModule.provideGson())
            .create(VerficationCodeApiClient1::class.java)
    val call: Call<Int> = verifyCodeApi.verifyValidationCode(userPhone, Code, userName)
     val view = popupWindow.contentView
    call.enqueue(object : Callback<Int> {
        @SuppressLint("UseCompatLoadingForDrawables")
        override fun onResponse(call: Call<Int>, response: Response<Int>) {
            if (response.isSuccessful) {
                if (response.body() == 1) {
                    popupWindow.dismiss()
                    Hawk.put("logIn" , 1)
                    Hawk.put("phoneNumber" , userPhone)
                } else {

                }
            }
        }

        override fun onFailure(call: Call<Int>, t: Throwable) {

            Toast.makeText(view.context, t.message.toString(), Toast.LENGTH_SHORT)
                .show()
        }

    })


}

 fun requestToSendValidationCode(view: View) {


    val verifyCodeApi: VerficationCodeApiClient1 =
        AppModule.provideRetrofit(AppModule.provideGson())
            .create(VerficationCodeApiClient1::class.java)
    val call: Call<Void> =
        verifyCodeApi.sendValidationCode(view.phone_number_edt_input.text.toString())
    call.enqueue(object : Callback<Void> {
        @SuppressLint("UseCompatLoadingForDrawables")
        override fun onResponse(call: Call<Void>, response: Response<Void>) {
            if (response.isSuccessful) {
                view.verification_input_layout.setVisible()
            }
        }

        override fun onFailure(call: Call<Void>, t: Throwable) {
            Toast.makeText(view.context, "لطفا دوباره تلاش کنید", Toast.LENGTH_SHORT)
                .show()
        }

    })


}
