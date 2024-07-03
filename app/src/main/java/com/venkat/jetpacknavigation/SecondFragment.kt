package com.venkat.jetpacknavigation
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.venkat.jetpacknavigation.databinding.FragmentSecondBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentSecondBinding? =null
    private var TAG= "SecondFragment"
    var get_email=""
    var get_otp=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            get_email = it.getString("email")?:""
            get_otp = it.getString("OTP")?:""
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.emailData?.setText("Please Enter the Otp Received at \n ${get_email}")
        binding?.firstBtn?.doOnTextChanged { text, start, before, count ->
            var firstText = binding?.firstBtn?.text?.toString()
            if (firstText?.length == 1) {
                binding?.secondBtn?.requestFocus()
            }
        }
        binding?.secondBtn?.doOnTextChanged { text, start, before, count ->
            var secondText = binding?.secondBtn?.text?.toString()
            if (secondText?.length == 1) {
                binding?.thirdBtn?.requestFocus()
            }
        }
        binding?.thirdBtn?.doOnTextChanged { text, start, before, count ->
            var thirdText = binding?.thirdBtn?.text?.toString()
            if (thirdText?.length == 1) {
                binding?.fourBtn?.requestFocus()
            }
        }
        binding?.fourBtn?.doOnTextChanged { text, start, before, count ->
            var fourText = binding?.fourBtn?.text?.toString()
            if (fourText?.length == 1) {
                binding?.fourBtn?.requestFocus()
            }
        }
        binding?.Check?.setOnClickListener{
            val data_firstbtn  = binding?.firstBtn?.text.toString()
            val data_secondbtn = binding?.secondBtn?.text.toString()
            val data_thirdbtn = binding?.thirdBtn?.text.toString()
            val data_fourbtn = binding?.fourBtn?.text.toString()
            val total = data_firstbtn + data_secondbtn + data_thirdbtn + data_fourbtn
            if (get_otp == total){
                Log.d(TAG, "true")
                var customDialog = Dialog(requireContext()).apply {
                    setContentView(R.layout.customdialogfragment1)
                    window?.setLayout(900,700)
                }.show()
                Toast.makeText(requireContext(), "OTP is matched", Toast.LENGTH_SHORT).show()
            }else{
                Dialog(requireContext()).apply {
                    setContentView(R.layout.customdialogfragment2)
                    window?.setLayout(900,700)
                }.show()
                Log.d(TAG ,total)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters

        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}