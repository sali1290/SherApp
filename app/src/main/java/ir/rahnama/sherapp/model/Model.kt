package ir.rahnama.sherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "poetry_table")
data class PoetModel(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id :Int ,
    @SerializedName("category_id")
    var category_id : String ,
    @SerializedName("name")
    var name : String ,
    @SerializedName("body")
    var body : String ,
    @SerializedName("poet_type")
    var poet_type : String ,
    @SerializedName("image_url")
    var image :String){}

@Entity(tableName = "selection_poetry")
data class SelectionPoetryModel(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id :Int ,
    @SerializedName("category_id")
    var category_id : String ,
    @SerializedName("poet_type")
    var poet_type :String ,
    @SerializedName("name")
    var name : String ,
    @SerializedName("image_url")
    var image :String){}

@Entity(tableName = "books")
data class BookModel(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("row")
    var row : Int ,
    @SerializedName("id")
    var id :String ,
    @SerializedName("category_id")
    var category_id :String ,
    @SerializedName("body")
    var body : String ,
    @SerializedName("kind")
    var kind :Int){}

@Entity(tableName = "book_content")
data class BookContentModel (
    @PrimaryKey(autoGenerate = true)
    @SerializedName("row")
    var row :Int ,
    @SerializedName("id")
    var id : String ,
    @SerializedName("category_id")
    var catgeory_id : Int ,
    @SerializedName("body")
    var body :String){}

@Entity(tableName =  "poem_body")
data class PoemBodyModel(
    @SerializedName("category_id")
    var category_id : String ,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("order")
    var order : Int ,
    @SerializedName("position")
    var position :Int ,
    @SerializedName("body")
    var body : String){}


@Entity(tableName = "sub")
data class Subscribtion(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id :Int  ,
    @SerializedName("title")
    var title : String ,
    @SerializedName("lenght")
    var length : String ,
    @SerializedName("detail_1")
    var detail_1 :String ,
    @SerializedName("detail_2")
    var detail_2: String,
    @SerializedName("detail_3")
    var detail_3 :String ,
    @SerializedName("price")
    var price : String ,
    @SerializedName("priceAmount")
    var priceAmount : Double ,
    @SerializedName("image")
    var image: String
)

@Entity(tableName = "negar")
data class Negare(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int ,
    @SerializedName("negarCount")
    var negarCount : Int ,
    @SerializedName("price")
    var price: String ,
    @SerializedName("priceAmount")
    var priceAmount : Double ,
    @SerializedName("off")
    var off : Boolean ,
    @SerializedName("offAmount")
    var offAmount : Double
)

@Entity(tableName = "ticket_list" )
data class TicketListModel(
    @SerializedName("id")
    val id:Int,
    @SerializedName("phone")
    val phone:String,
    @SerializedName("ticket_title")
    val ticket_title:String,
    @SerializedName("ticket_state")
    val ticket_state :Int,
    @SerializedName("message_notify")
    val message_notify :Int
    )
@Entity(tableName = "user_messages")
data class TicketMessageModel(
    @SerializedName("message")
    val message:String,
    @SerializedName("time")
    val time:String,
    @SerializedName("message_type")
    val message_type:Int
    )

@Entity(tableName = "user_profile")
data class User(
    @SerializedName("id")
    val id : Int ,
    @SerializedName("name")
    val name : String ,
    @SerializedName("phone")
    val phone : String ,
    @SerializedName("sub_type")
    val subType : Int ,
    @SerializedName("user_negare")
    val negareCount : Int
)

data class SearchModel(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("category_id")
    var category_id: Int?,
    @SerializedName("body")
    var body : String?){}

@Entity(tableName = "poster")
data class PosterModel(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id:Int?,
    @SerializedName("category_id")
    var category_id: Int?,
    @SerializedName("biography_id")
    var biography_id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("clickable")
    var clickable: Int? =null,
    @SerializedName("image_url")
    var image:String?){}
