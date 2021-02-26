package ir.org.tavanesh.presentation.base.main

import android.animation.ValueAnimator
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.org.tavanesh.R
import ir.org.tavanesh.R.id.*
import ir.org.tavanesh.core.utils.*
import ir.org.tavanesh.databinding.ActivityMainBinding
import ir.org.tavanesh.ext.getMessage
import ir.org.tavanesh.ext.hideKeyboard
import ir.org.tavanesh.ext.loadCircle
import ir.org.tavanesh.presentation.dialog.DialogDescription
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_sliding_menu.view.*
import kotlin.system.exitProcess
import ir.org.tavanesh.core.utils.Toast as Toast1


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var navController: NavController
    private var currentFragment: Int = 0
    private var drawerGravity = Gravity.RIGHT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        initializeNavigation()

        observeUseCases()

        slidingMenuInit()

        initPushHandler()
    }

    private var canExit = false
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(drawerGravity)) {
            drawerLayout.closeDrawer(drawerGravity)
        }
        else {
            when (currentFragment) {
                splashFragment, takeExamFragment -> {
                }
                examResultFragment -> {
                    viewModel.navigateTo(action_examResultFragment_to_dashboardFragment)
                }
                authMenuFragment, dashboardFragment -> {
                    if (canExit) {
                        this.finish()
                        exitProcess(1)
                    }
                    else {
                        canExit = true
                        showToast(getString(R.string.hint_exit_application))
                    }
                }
                videoPlayerFragment ->{
                    navController.popBackStack(currentFragment, true)
                    this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                }
                else -> {
                    navController.popBackStack(currentFragment, true)
                    /*if(isNavFromPush){
                        isNavFromPush = false
                        val navGraph: NavGraph =
                            navController.navInflater.inflate(R.navigation.navigation_graph)
                        navController.graph = navGraph
                    }else
                        navController.popBackStack(currentFragment, true)*/
                }
            }
        }
    }

    private fun initializeNavigation() {

        navController = findNavController(R.id.nav_host_fragment)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentFragment = destination.id
            when (currentFragment) {
                dashboardFragment -> {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                }
                else -> {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
            }

            txtToolbar.text = destination.label
            when (destination.id) {
                splashFragment, introFragment, verificationFragment, forgetPasswordFragment,
                loginFragment, registerFragment, authMenuFragment, dashboardFragment,
                videoPlayerFragment -> {
                    toolbar.visibility = View.GONE
                }
                else -> {
                    toolbar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun animateAppbar(dimen:Int){
        val anim = ValueAnimator.ofInt(toolbar.measuredHeight, dimen)
        anim.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            val layoutParams: ViewGroup.LayoutParams = toolbar.layoutParams
            layoutParams.height = value
            toolbar.layoutParams = layoutParams
        }
        anim.duration = 320
        anim.start()
    }

    private fun observeUseCases() {

        viewModel.provideAlert().observe(this, {
            it?.let { alert ->
                when (alert) {
                    is Toast1 -> {
                        showToast(alert.message)
                    }
                    is Dialog -> {
                        DialogDescription(this, alert.message).show()
                    }
                    //todo: other kinds
                }
            }
        })

        viewModel.provideNavigation().observe(this, {
            it?.let { navPage ->
                toolbar.hideKeyboard()
                drawerLayout.closeDrawer(drawerGravity)
                when (navPage) {
                    is Navigate -> {
                        navController.navigate(navPage.destinationId, navPage.bundle)
                    }
                    is PopBack -> {
                        onBackPressed()
                    }
                    is Logout -> {
                        viewModel.logout()
                    }
                    is SlidingMenu -> {
                        openDrawer()
                    }
                }
            }
        })

        viewModel.provideProgress().observe(this, {
            it?.let { isProgress ->
                if (isProgress) progressBar.visibility = View.VISIBLE
                else progressBar.visibility = View.GONE
            }
        })

        viewModel.provideToolbar().observe(this, { it ->
            it?.let { esToolbar ->
                esToolbar.titleText?.let {
                    txtToolbar.text = it
                } ?: run {
                    esToolbar.titleResource?.let { textID ->
                        txtToolbar.text = getText(textID)
                    }
                }
                esToolbar.rightLead?.let { rightLead ->
                    btnRight.visibility = View.VISIBLE
                    btnRight.setImageResource(rightLead.iconResource)
                    btnRight.setOnClickListener {
                        rightLead.callback()
                    }
                } ?: run {
                    btnRight.visibility = View.INVISIBLE
                    btnRight.setOnClickListener { }
                }
                esToolbar.leftLead?.let { leftLead ->
                    btnLeft.visibility = View.VISIBLE
                    btnLeft.setImageResource(leftLead.iconResource)
                    btnLeft.setOnClickListener {
                        leftLead.callback()
                    }
                } ?: run {
                    btnLeft.visibility = View.INVISIBLE
                    btnLeft.setOnClickListener { }
                }
            }
        })

        viewModel.provideFailure().observe(this, {
            it?.let { failure ->
                val message = failure.getMessage(this)
                when (failure) {
                    is ProviderFailure -> {
                        viewModel.showDialog(message)
                    }
                    is BanFailure -> {
                        DialogDescription(
                            this, message, false,
                            callback = {
                                this.finish()
                            }
                        ).show()
                    }
                    is AuthFailure -> {
                        DialogDescription(
                            this, message, false,
                            callback = {
                                viewModel.logout()
                            }
                        ).show()
                    }
                    is ServerFailure, is InvalidInputFailure, is ResponseFailure, is NoParamsFailure, is UnKnownFailure, is ParseFailure -> {
                        showToast(message)
                    }
                }

            }
        })

        viewModel.provideKeyboardCloseRequest().observe(this, {
            it?.let {
                toolbar.hideKeyboard()
            }
        })

    }

    private fun showToast(message: String) {
        val inflater = layoutInflater
        val layout: View = inflater.inflate(R.layout.view_toast, null)
        val text: TextView = layout.findViewById(R.id.txtToast)
        text.text = message
        with(Toast(applicationContext)) {
            view = layout
            show()
        }
    }

    private fun openDrawer(){
        drawerLayout.openDrawer(drawerGravity)
    }

    private fun slidingMenuInit() {
        val slidingMenu = navView.getHeaderView(0)

        slidingMenu.txtEditProfile.setOnClickListener {
            viewModel.navigateTo(profileFragment)
        }

        slidingMenu.btnPayHistory.setOnClickListener {}

        slidingMenu.btnArchives.setOnClickListener {
            viewModel.navigateTo(consultHistoryFragment)
        }

        slidingMenu.btnRules.setOnClickListener {
            viewModel.navigateTo(privacyPolicyFragment)
        }

        slidingMenu.btnSupport.setOnClickListener {
            viewModel.navigateTo(supportFragment)
        }

        slidingMenu.btnAboutUs.setOnClickListener {
            viewModel.navigateTo(supportFragment)
        }

        slidingMenu.btnExit.setOnClickListener {
            viewModel.logout()
        }

        viewModel.user.observe(this, {
            it?.let {
                slidingMenu.txtName.text = it.firstName + " " + it.lastName
                slidingMenu.ivAvatar.loadCircle(it.avatar)
            }
        })
        viewModel.getUserInfo()
    }

    /*push handler*/
    private fun initPushHandler(){

    }

}