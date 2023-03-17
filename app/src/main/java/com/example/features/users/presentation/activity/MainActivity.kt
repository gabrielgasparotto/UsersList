package com.example.features.users.presentation.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.features.app.extensions.gone
import com.example.features.app.extensions.visible
import com.example.userslist.R
import com.example.userslist.databinding.ActivityMainBinding
import com.example.features.users.domain.model.User
import com.example.features.users.presentation.adapter.UserListAdapter
import com.example.features.users.presentation.viewmodel.UserViewModel
import com.example.features.users.presentation.viewmodel.UserViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { UserListAdapter() }
    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        observeStates()
        initViewModel()
    }

    private fun initViewModel() {
        userViewModel.getUsers()
    }

    private fun observeStates() {
        userViewModel.state.observe(this) { state ->
            state?.let {
                when (it) {
                    is UserViewState.UserSuccess -> showUserList(it.users)
                    is UserViewState.UserError -> setupError(
                        it.error.message ?: getString(R.string.error)
                    )
                }
            }
        }
    }

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
}
