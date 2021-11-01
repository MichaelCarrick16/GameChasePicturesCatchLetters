package com.example.gamechasepicturescatchletters.ui.fragment.menu

import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.util.*

class MenuAndDetailViewModel : ViewModel() {
     var resultCheckMediaPlayer : Boolean = false


     // Handle insert Ruby
     val currentRuby : MutableLiveData<Int> by lazy {
          MutableLiveData<Int>(50)
     }
     fun insertRuby(){
          currentRuby.value = currentRuby.value!! + 50
     }


     // Handle Time Reply Question
     val timeQuestion : MutableLiveData<Int> by lazy {
          MutableLiveData<Int>(60)
     }
     fun timeQuestion(){
          var resultDefault : Int? = timeQuestion.value
          viewModelScope.launch(Dispatchers.IO) {
               var check : Boolean = true
               while (check){
                    resultDefault = resultDefault!! - 1
                    timeQuestion.postValue(resultDefault)
                    delay(1000L)
                    if(resultDefault == 0){
                         check = false
                         timeQuestion.postValue(60)
                    }
               }
          }
     }

     //Get icon from assets
     private var listIcon : MutableList<String> = mutableListOf()
     @JvmName("getListIcon1")
     fun getListIcon(context : Context) : MutableList<String>{
          try {
               for (icon : String in context.assets.list("icon")!!){
                    var iconName : String = "icon/"+icon
                    listIcon.add(iconName)
               }
          }catch (e : Exception){
               e.printStackTrace()
          }
          return listIcon
     }

     // Get text from assets
     var listTitle : MutableList<String> = mutableListOf()
     fun getListText(context: Context) : MutableList<String>{
          var text : String = ""
          try {
               var inputStream : InputStream = context.assets.open("data/result.txt")
               var size : Int = inputStream.available()
               var buffer = ByteArray(size)
               inputStream.read(buffer)
               text = String(buffer)
          }catch (e : Exception){
               e.printStackTrace()
          }
          var listSplit = text.split("\n")
          for (str : String in listSplit){
               listTitle.add(str)
          }
          return listTitle
     }


     // Index of question
     var index : Int = 0

     // Get Elements of Result
     var listHandleRecyclerViewResult = mutableListOf<String>()
     fun getListElementRecyclerViewResult() : MutableList<String>{
          var str : String = listTitle.get(index)
          var count : Int = 0
          while (count<str.length-1){
               listHandleRecyclerViewResult.add("?")
               count++
          }
          return listHandleRecyclerViewResult
     }

     // Get Element of Reply
     var listHandleRecyclerViewReply = mutableListOf<String>()
     fun getListElementRecyclerViewReply() : MutableList<String>{
          var result : String = listTitle.get(index)
          var random : Random = Random()

          // Random de cho du 14 vi tri
          for(i : Int in 0..100){
               var c = Math.abs(random.nextInt(89))
               if(c>=65){
                    result += c.toChar().toString()
                    if(result.length==14){
                         break;
                    }
               }
          }

          // sort index of element de cho kho doan hon
          var ch = CharArray(14)
          for(i : Int in 0..result.length-1){
               var c = result.get(i)
               ch[i] = c
          }
          Arrays.sort(ch)

          // do data vao list
          for (i : Int in 0..result.length-1){
               listHandleRecyclerViewReply.add(ch[i].toString())
          }

          return listHandleRecyclerViewReply 
     }

     // thay the gia tri tai vi tri cua result
     var replaceIndexOfResult : Int = 0

     // Handle khi click vao element Reply => Bắn lên cho thằng Result
     // Khi bắn đủ size Result phải next question
     // Handle Coroutine khi hết time thì next question , hoặc khi trả lời xong thì reset time
     // Handle khi back lại menu thì nó x2 lần recyclerview lên , time vẫn chạy

}