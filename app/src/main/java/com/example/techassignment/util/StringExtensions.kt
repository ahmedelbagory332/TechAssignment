package com.example.techassignment.util




fun String?.ifNull(placeHolder : String = ""): String {

    return if(this.isNullOrEmpty()){
        placeHolder
    }else{
        this
    }
}
fun Int?.ifNull(placeHolder : Int = 0): Int {
    return this ?: placeHolder
}




