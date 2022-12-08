package com.dicoding.tripinaja

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView

class DetailFragment : Fragment(), View.OnClickListener {
    private lateinit var ivImgDetail : ImageView
    private lateinit var btnBack : CircleImageView
    private lateinit var btnMapDetail : CircleImageView
    private lateinit var tvNameDetail : TextView
    private lateinit var tvCategoryDetail : TextView
    private lateinit var tvRatingDetail : TextView
    private lateinit var tvAddressDetail : TextView
    private lateinit var tvOpenDayDetail : TextView
    private lateinit var tvOpenTimeDetail : TextView
    private lateinit var tvDescriptionDetail : TextView

    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_CATEGORY = "extra_category"
        var EXTRA_RATING = "extra_rating"
        var EXTRA_DESCRIPTION = "extra_description"
        var EXTRA_ADDRESS = "extra_address"
        var EXTRA_OPENDAY = "extra_openday"
        var EXTRA_OPENTIME = "extra_opentime"
        var EXTRA_MAPLINK = "extra_maplink"
        var EXTRA_PHOTO = "extra_photo"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivImgDetail = view.findViewById(R.id.iv_img_dtl)
        btnBack = view.findViewById(R.id.btn_back)
        btnMapDetail = view.findViewById(R.id.btn_map_dtl)
        tvNameDetail = view.findViewById(R.id.tv_name_dtl)
        tvCategoryDetail = view.findViewById(R.id.tv_category_dtl)
        tvRatingDetail = view.findViewById(R.id.tv_rating_dtl)
        tvAddressDetail = view.findViewById(R.id.tv_address_dtl)
        tvOpenDayDetail = view.findViewById(R.id.tv_openday_dtl)
        tvOpenTimeDetail = view.findViewById(R.id.tv_opentime_dtl)
        tvDescriptionDetail = view.findViewById(R.id.tv_description_dtl)

        btnBack.setOnClickListener(this)
        btnMapDetail.setOnClickListener(this)

        if (arguments != null) {
            tvNameDetail.text = arguments?.getString(EXTRA_NAME)
            tvCategoryDetail.text = arguments?.getString(EXTRA_CATEGORY)
            tvRatingDetail.text = arguments?.getString(EXTRA_RATING)
            tvDescriptionDetail.text = arguments?.getString(EXTRA_DESCRIPTION)
            tvAddressDetail.text = arguments?.getString(EXTRA_ADDRESS)
            tvOpenDayDetail.text = arguments?.getString(EXTRA_OPENDAY)
            tvOpenTimeDetail.text = arguments?.getString(EXTRA_OPENTIME)
            val photo = arguments?.getInt(EXTRA_PHOTO,0)

            Glide.with(this)
                .load(photo)
                .apply(RequestOptions())
                .into(ivImgDetail)
        }

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val mHomeFragment = HomeFragment()
                val mFragmentManager = parentFragmentManager
                mFragmentManager.beginTransaction().apply {
                    replace(R.id.fragmentContainer, mHomeFragment, HomeFragment::class.java.simpleName)
                    commit()
                }
            }
        })

    }


    override fun onClick(view: View) {
        when(view.id) {
            R.id.btn_back -> {
                val mHomeFragment = HomeFragment()
                val mFragmentManager = parentFragmentManager
                mFragmentManager.beginTransaction().apply {
                    replace(R.id.fragmentContainer, mHomeFragment, HomeFragment::class.java.simpleName)
                    commit()
                }
            }
            R.id.btn_map_dtl -> {
                val mapLink = arguments?.getString(EXTRA_MAPLINK)
                val uri = Uri.parse(mapLink)
                val likeIng = Intent(Intent.ACTION_VIEW, uri)
                try {
                    startActivity(likeIng)
                } catch (e: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(mapLink)
                        )
                    )
                }
            }
            else -> {}
        }
    }
}