package ir.rahnama.sherapp.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zarinpal.ewallets.purchase.OnCallbackRequestPaymentListener
import com.zarinpal.ewallets.purchase.PaymentRequest
import com.zarinpal.ewallets.purchase.ZarinPal
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.NegarItemModelBinding
import ir.rahnama.sherapp.databinding.SubItemModelBinding
import ir.rahnama.sherapp.model.Negare
import ir.rahnama.sherapp.model.Subscribtion

class NegareAdapter :RecyclerView.Adapter<NegareAdapter.NegareMyViewHolder>() {


    var negarList : MutableList<Negare> = arrayListOf()


    fun refresh (newList : List<Negare>){
        negarList.clear()
        negarList.addAll(newList)
        Log.i("Test" , negarList.size.toString())
        notifyDataSetChanged()
    }
    inner class NegareMyViewHolder(var view : NegarItemModelBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NegareMyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NegareMyViewHolder(DataBindingUtil.inflate(inflater , R.layout.negar_item_model , parent , false))
    }

    override fun onBindViewHolder(holder: NegareMyViewHolder, position: Int) {
        holder.view.negar = negarList[position]
        Log.i("Test" , negarList[position].toString())

        holder.view.negarBuyLinearLayout.setOnClickListener(View.OnClickListener {
            //arrin pall ya bazar
            val purchase: ZarinPal = ZarinPal.getPurchase(holder.view.negarBuyLinearLayout.context)
            val payment: PaymentRequest = ZarinPal.getPaymentRequest()
            payment.merchantID = "1a76a7c5-e7ad-414e-88d1-3706916a1905"
            payment.amount = negarList[position].priceAmount.toLong()
            payment.description = "پرداخت جهت تست"
            payment.setCallbackURL("return://zarinpalpayment")

            purchase.startPayment(
                payment,
                OnCallbackRequestPaymentListener { status, authority, paymentGatewayUri, intent ->
                    if (status == 100) {
                        startActivity(holder.view.negarBuyLinearLayout.context, intent, null)
                    } else {
                        val toast = Toast(holder.view.negarBuyLinearLayout.context)
                        toast.setText("خطا در پرداخت")
                        toast.duration = Toast.LENGTH_SHORT
                        toast.show()
                    }
                })
        })
    }

    override fun getItemCount(): Int = negarList.size
}