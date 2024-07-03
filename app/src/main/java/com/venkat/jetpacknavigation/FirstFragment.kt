package com.venkat.jetpacknavigation


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.venkat.jetpacknavigation.databinding.FragmentFirstBinding
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentFirstBinding? =null
    private val TAG="FirstFragment"
    var read_data=""

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
        binding = FragmentFirstBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.otpButton?.setOnClickListener{
            if(binding?.enterEmail?.text?.isNullOrEmpty() == true){
                binding?.enterEmail?.error = "Please enter the E-Mail"
            } else{
                checkValidation()
                if(checkValidation()){
                    randomCode()
                    var implicit_intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:")
                        putExtra(Intent.EXTRA_EMAIL, arrayOf(binding?.enterEmail?.text?.trim().toString()))
                        putExtra(Intent.EXTRA_TEXT,"Your OTP is ${read_data}")
                        putExtra(Intent.EXTRA_SUBJECT,"OTP")
                    }
                    startActivity(implicit_intent)
                    var bundle = Bundle()
                    bundle.putString("email",binding?.enterEmail?.text?.trim().toString())
                    bundle.putString("OTP",read_data)
                    findNavController().navigate(R.id.first_to_second , bundle)

                }
            }
        }
    }
    private fun randomCode() {
        val LENGTH = 4
        var read_val =""
        for(i in 1..LENGTH){
            read_val += Random.nextInt(0,9)
        }
        read_data= read_val
        Log.d(TAG, read_data)
    }
    private fun checkValidation(): Boolean {
        var username = binding?.enterEmail?.text?.trim().toString()
        if (Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            Toast.makeText(requireContext(), "Email Verified !", Toast.LENGTH_SHORT).show()
            return true
        }else{
            Toast.makeText(requireContext(), "Enter valid email address!", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}