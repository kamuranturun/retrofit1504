package com.turun.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView= findViewById<RecyclerView>(R.id.myrecyclerView)


        val serviceGenerator= ServiceGenerator.buildService(ApiService::class.java)
        val call= serviceGenerator.getPosts()


            call.enqueue(object:Callback<MutableList<PostModel>>{
                override fun onResponse(
                    call: Call<MutableList<PostModel>>,
                    response: Response<MutableList<PostModel>>
                ) {
                    if(response.isSuccessful){
                        recyclerView.apply {
                            layoutManager= LinearLayoutManager(this@MainActivity)
                            adapter= PostAdapter(response.body()!!)
                        }
                    }
                }

                override fun onFailure(call: Call<MutableList<PostModel>>, t: Throwable) {
                    t.printStackTrace()
                    println("hata"+t.message.toString())
                }

            })


    }
}