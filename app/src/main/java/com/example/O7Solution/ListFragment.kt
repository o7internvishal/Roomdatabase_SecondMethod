package com.example.O7Solution

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.O7Solution.data.User
import com.example.O7Solution.data.UserDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var list = ArrayList<User>()
    lateinit var etFirstName: EditText
    lateinit var etLastName: EditText
    lateinit var etAge: EditText
    lateinit var btnAdd: Button
    lateinit var tvID: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        etFirstName = view.findViewById(R.id.etFirstName)
        etLastName = view.findViewById(R.id.etLastName)
        etAge = view.findViewById(R.id.etAge)
        //  tvID = view.findViewById(R.id.tvId)
        btnAdd = view.findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener {

            class getTask : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg params: Void?): Void? {
                    //    val id = tvID.text.toString().toInt()
                    val firstname = etFirstName.text.toString()
                    val lastname = etLastName.text.toString()
                    val age = etAge.text.toString().toInt()


                    list.addAll(UserDatabase.getDatabase(requireContext()).UserDao().readAlldata())

                    UserDatabase.getDatabase(requireContext()).UserDao()
                        .addUser(User(firstname = firstname, lastname = lastname, age = age))

                    return null

                }

                override fun onPostExecute(result: Void?) {
                    super.onPostExecute(result)
                    Toast.makeText(requireContext(), "Successfully Added", Toast.LENGTH_SHORT)
                        .show()
                }


            }

            getTask().execute()
            findNavController().navigate(R.id.action_listFragment_to_homeFragment)

        }
        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        class getTask :AsyncTask<Void, Void, Void>(){
//            override fun onPreExecute() {
//                super.onPreExecute()
//
//            }
//            override fun doInBackground(vararg params: Void?): Void? {
//                list.addAll(   UserDatabase.getDatabase(requireContext()).UserDao().readAlldata())
//
//                UserDatabase.getDatabase(requireContext()).UserDao().addUser(User("","",0))
//                return null
//            }
//
//            override fun onPostExecute(result: Void?) {
//                super.onPostExecute(result)
//                //toast
//            }
//
//            override fun onProgressUpdate(vararg values: Void?) {
//                super.onProgressUpdate(*values)
//            }
//        }
//
//        getTask().execute();
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}