package ir.rahnama.sherapp.view

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.orhanobut.hawk.Hawk
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.FragmentListOfTicketBinding
import ir.rahnama.sherapp.di.AppModule
import ir.rahnama.sherapp.model.TicketListModel
import ir.rahnama.sherapp.model.remote.VerficationCodeApiClient1
import ir.rahnama.sherapp.utiles.autoCleared
import ir.rahnama.sherapp.utiles.checkUser
import ir.rahnama.sherapp.view.adapter.ListOfTicketAdapter
import kotlinx.android.synthetic.main.add_new_ticket_alertdialog.view.*
import kotlinx.android.synthetic.main.fragment_list_of_ticket.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListOfTicketFragment : Fragment(), ListOfTicketAdapter.OpenTicket {


    private lateinit var adapter: ListOfTicketAdapter
    var binding: FragmentListOfTicketBinding by autoCleared()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListOfTicketBinding.inflate(inflater, container, false)
        //init adapter
        adapter = ListOfTicketAdapter(requireActivity(), this)
        binding.TicketListRecyclerView.adapter = adapter
        checkUser(requireContext())
        binding.refreshData.setOnRefreshListener {
            if (Hawk.contains("phoneNumber")) {
                requestForTicketList(Hawk.get("phoneNumber")!!)
            }
            Handler().postDelayed({
                if (binding.refreshData.isRefreshing) {
                    binding.refreshData.isRefreshing = false
                }
            }, 1000)
        }
        binding.btnAddNewTicket.setOnClickListener { addTicketAlertDialog() }
        binding.addTicketBtnBack.setOnClickListener { popBackStack() }
        binding.firstTimeAddNewTicket.setOnClickListener { addTicketAlertDialog() }
        return binding.root
    }


    private fun addTicketAlertDialog() {

        val builder = AlertDialog.Builder(requireActivity()).create()
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.add_new_ticket_alertdialog, null)
//        val spinner = dialogLayout.findViewById<Spinner>(R.id.add_ticket_spinner)
//        val txt_show_toLogin = dialogLayout.findViewById<TextView>(R.id.txt_show_toLogin)
//        val spinner_layout = dialogLayout.findViewById<ConstraintLayout>(R.id.spinner_layout)
//        val btn_add_ticket = dialogLayout.findViewById<Button>(R.id.add_ticket_btn_create)
//        val add_ticket_edt_title = dialogLayout.findViewById<TextInputEditText>(R.id.add_ticket_edt_title)
//        val title_textinputLayout = dialogLayout.findViewById<TextInputLayout>(R.id.title_textinputLayout)

        var toDoLogIn = false

        if (Hawk.contains("logIn")) {
            dialogLayout.txt_show_toLogin.visibility = View.INVISIBLE
        } else {
            dialogLayout.title_textinputLayout.visibility = View.INVISIBLE
            dialogLayout.spinner_layout.visibility = View.INVISIBLE
            dialogLayout.add_ticket_btn_create.text = "ساخت حساب"
            toDoLogIn = true
        }
        val items = arrayOf("مشکلات فنی", "مشکلات پرداخت", "محتوای برنامه", "سایر")
        val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, items)
        dialogLayout.add_ticket_spinner.adapter = adapter


        var titleValid = false

        dialogLayout.add_ticket_edt_title.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                dialogLayout.title_textinputLayout.isErrorEnabled = false
            }

            override fun afterTextChanged(p0: Editable?) {
                if (dialogLayout.add_ticket_edt_title.text.toString().isEmpty()) {
                    dialogLayout.title_textinputLayout.isErrorEnabled = true
                    dialogLayout.title_textinputLayout.error = "موضوع تیکت را وارد کنید"
                    titleValid = false
                } else {
                    dialogLayout.title_textinputLayout.isErrorEnabled = false
                    titleValid = true
                }
            }
        })


         dialogLayout.add_ticket_btn_create.setOnClickListener{


             if (toDoLogIn){
                 builder.dismiss()
             }else{
                 if (titleValid){
                     requestForAddTicket(
                         Hawk.get("userName"),
                         Hawk.get("phoneNumber"),
                         dialogLayout.add_ticket_edt_title.text.toString(),
                         dialogLayout.add_ticket_spinner.selectedItemPosition.toString()
                     )
                     builder.dismiss()
                 }else{
                     Toast.makeText(requireActivity(), "لطفا اطلاعات را به صورت صحیح وارد کنید", Toast.LENGTH_SHORT).show()
                 }
             }





         }

        builder.setView(dialogLayout)
        builder.show()
    }

    private fun popBackStack() {
        //val fragmentManager: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        requireActivity().supportFragmentManager.popBackStack()
        //fragmentManager.remove(this).commit()
        requireActivity().onBackPressed()
    }


    private  fun requestForTicketList(phone: String) {
        if (Hawk.contains("phoneNumber")) {
            val apiService: VerficationCodeApiClient1 = AppModule.provideRetrofit(AppModule.provideGson()).create(VerficationCodeApiClient1::class.java)
            val call: Call<MutableList<TicketListModel>> = apiService.getTicketList(phone)
            call.enqueue(object : Callback<MutableList<TicketListModel>> {
                override fun onResponse(
                        call: Call<MutableList<TicketListModel>>,
                        response: Response<MutableList<TicketListModel>>
                ) {
                    Log.i("test", response.toString())
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            TicketListRecyclerView.adapter = adapter
                            TicketListRecyclerView.layoutManager =
                                    LinearLayoutManager(requireContext())
                            adapter.setItems(response.body()!!)
                        }

                    }
                }

                override fun onFailure(call: Call<MutableList<TicketListModel>>, t: Throwable) {
                    Snackbar.make(
                            ticket_coordinator_layout,
                            "مطمئنی اینترنتت وصله؟ ",
                            Snackbar.LENGTH_LONG
                    ).show()
                    Log.i("test", t.message.toString())
                }
            })

        }

    }


    private fun requestForAddTicket(name: String, phone: String, title: String, category: String){

        val apiService: VerficationCodeApiClient1 = AppModule.provideRetrofit(AppModule.provideGson()).create(VerficationCodeApiClient1::class.java)
        val call:Call<Void> = apiService.addTicket(name, phone, title, category)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    requestForTicketList(Hawk.get("phoneNumber")!!)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Snackbar.make(
                    ticket_coordinator_layout,
                    "مطمئنی اینترنتت وصله؟ ",
                    Snackbar.LENGTH_LONG
                ).show()
            }


        })


    }


    private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun openTicket(id: Int, State: Int) {
        loadFragment(ShowTicketContentFragment(id, State))
    }


}

