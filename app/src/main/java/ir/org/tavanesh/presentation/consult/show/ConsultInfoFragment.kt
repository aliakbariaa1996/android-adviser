package ir.org.tavanesh.presentation.consult.show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.consult.entity.Consult
import ir.org.tavanesh.core.utils.EsToolbar
import ir.org.tavanesh.core.utils.OBJECT
import ir.org.tavanesh.core.utils.ToolbarButton
import ir.org.tavanesh.databinding.FragmentConsultInfoBinding
import kotlinx.android.synthetic.main.fragment_consult_info.*
import kotlinx.coroutines.*

@AndroidEntryPoint
class ConsultInfoFragment : Fragment() {

    private val viewModel: ConsultInfoViewModel by viewModels()
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentConsultInfoBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setToolbar(
            EsToolbar(
                titleResource = R.string.hint_reserve_appointment_detail,
                leftLead = ToolbarButton(
                    R.drawable.ic_close_gray,
                    callback = { viewModel.back() }
                )
            )
        )

        val consult:Consult = arguments?.getSerializable(OBJECT) as Consult
        viewModel.setConsult(consult)

        initMap(savedInstanceState)
    }


    private fun initMap(savedInstanceState: Bundle?) {

        viewModel.setProgress(true)

        val mapJob = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                mapView?.onCreate(savedInstanceState)
                mapView.onResume()

                try {
                    MapsInitializer.initialize(context)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                mapView.getMapAsync {
                    googleMap = it
                    viewModel.setProgress(false)
                }
            }
        }

        mapJob.invokeOnCompletion {
            CoroutineScope(Dispatchers.Main).launch {

                viewModel.getConsultPlaceLatLng()?.let{
                    googleMap.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            it,
                            16f
                        )
                    )
                    googleMap.addMarker(MarkerOptions().position(it))
                }

                this.cancel()
                mapJob.cancelAndJoin()
            }
        }

    }

}