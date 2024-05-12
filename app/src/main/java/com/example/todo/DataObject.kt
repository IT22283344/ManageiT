package com.example.todo

import android.icu.text.CaseMap.Title

object DataObject {
    var lisdata= mutableListOf<CardInfo>()

    fun setData(title: String,description: String,priority: String){
        lisdata.add(CardInfo(title,priority, description ))
    }

    fun getAllData():List<CardInfo>{
       return lisdata
    }

    fun deleteAll(){
        lisdata.clear()
    }

    fun getData(pos:Int):CardInfo{
        return lisdata[pos]
    }

    fun deleteData(pos:Int){
        lisdata.removeAt(pos)
    }

    fun updateData(pos:Int,title: String,description: String,priority: String){
        lisdata[pos].title=title
        lisdata[pos].description=description
        lisdata[pos].priority=priority
    }
}