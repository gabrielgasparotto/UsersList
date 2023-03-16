package com.example.features.users.presentation.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.features.app.extensions.gone
import com.example.features.app.extensions.visible
import com.example.userslist.R
import com.example.userslist.databinding.ActivityMainBinding
import com.example.features.users.domain.model.User
import com.example.features.users.presentation.adapter.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { UserListAdapter() }
//    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        showUserList(usersList())
//        observeStates()
//        initViewModel()
    }

//    private fun initViewModel() {
//        userViewModel.init()
//    }
//
//    private fun observeStates() {
//        userViewModel.state.observe(this, Observer { state ->
//            state?.let {
//                when (it) {
//                    is UserViewState.UserSuccess -> showUserList(it.users)
//                    is UserViewState.UserEmptyError -> setupError(
//                        it.error.message ?: getString(R.string.error)
//                    )
//                    else -> setupError(getString(R.string.error))
//                }
//            }
//        })
//    }

    private fun initViews() {
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            userListProgressBar.visible()
        }
    }

    private fun showUserList(users: List<User>) {
        binding.userListProgressBar.gone()
        adapter.users = users
    }

    private fun setupError(message: String) {
        hideViews()
        showErrorToast(message)
    }

    private fun showErrorToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun hideViews() {
        binding.apply {
            userListProgressBar.gone()
            recyclerView.gone()
        }
    }

    private fun usersList() = arrayListOf(
        User(
            id = 1,
            illustration = "https://avatars.githubusercontent.com/u/1?v=4",
            name = "Oi",
            username = "Bom dia"
        ),
        User(
            id = 2,
            illustration = "https://avatars.githubusercontent.com/u/2?v=4",
            name = "Tchau",
            username = "Boa Noite"
        )
    )
}
