package com.dicoding.tripinaja

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import de.hdodenhof.circleimageview.CircleImageView


class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var btnGmail: CircleImageView
    private lateinit var btnInstagram: CircleImageView
    private lateinit var btnGithub: CircleImageView
    private lateinit var btnLinkedin: CircleImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGmail = view.findViewById(R.id.gmail_profile)
        btnInstagram = view.findViewById(R.id.instagram_profile)
        btnGithub = view.findViewById(R.id.github_profile)
        btnLinkedin = view.findViewById(R.id.linkedin_profile)

        btnGmail.setOnClickListener(this)
        btnInstagram.setOnClickListener(this)
        btnGithub.setOnClickListener(this)
        btnLinkedin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.gmail_profile -> {
                val emailIntent = Intent(
                    Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "danangipulbasrin@gmail.com", null
                    )
                )
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is my subject text")
                requireContext().startActivity(Intent.createChooser(emailIntent, null))
            }
            R.id.instagram_profile -> {
                val uri = Uri.parse("https://www.instagram.com/rkuning_/")
                val likeIng = Intent(Intent.ACTION_VIEW, uri)
                likeIng.setPackage("com.instagram.android")
                try {
                    startActivity(likeIng)
                } catch (e: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/rkuning_/")
                        )
                    )
                }
            }
            R.id.github_profile -> {
                val uri = Uri.parse("https://github.com/rkuning")
                val likeIng = Intent(Intent.ACTION_VIEW, uri)
                try {
                    startActivity(likeIng)
                } catch (e: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://github.com/rkuning")
                        )
                    )
                }
            }
            R.id.linkedin_profile -> {
                val uri = Uri.parse("https://linkedin.com/in/rkuning")
                val likeIng = Intent(Intent.ACTION_VIEW, uri)
                likeIng.setPackage("com.linkedin.android")
                try {
                    startActivity(likeIng)
                } catch (e: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://linkedin.com/in/rkuning")
                        )
                    )
                }
            }
            else -> {}
        }
    }

}
