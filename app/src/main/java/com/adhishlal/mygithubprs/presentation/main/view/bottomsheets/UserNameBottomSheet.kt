package com.adhishlal.mygithubprs.presentation.main.view.bottomsheets

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.adhishlal.mygithubprs.R
import com.adhishlal.mygithubprs.data.utils.Constants.USER_ID
import com.adhishlal.mygithubprs.databinding.BottomSheetUserNameBinding
import com.adhishlal.mygithubprs.presentation.main.view.activities.PublicRepositoriesActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UserNameBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetUserNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetUserNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getRepositories.setOnClickListener {
            if (binding.userName.text.toString().trim().isBlank().not()) {
                val intent = Intent(this.context, PublicRepositoriesActivity::class.java)
                intent.putExtra(USER_ID, binding.userName.text.toString())
                startActivity(intent)

                dismissAllowingStateLoss()
            } else {
                Toast.makeText(
                    this.context,
                    resources.getString(R.string.enter_user_name_to_continue),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}