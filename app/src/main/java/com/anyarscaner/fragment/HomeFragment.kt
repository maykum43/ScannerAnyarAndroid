package com.anyarscaner.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.anyarscaner.R
import com.anyarscaner.activity.*
import com.anyarscaner.adapter.AdapterContent
import com.anyarscaner.adapter.ImageSliderAdapter
import com.anyarscaner.data.ImageData
import com.anyarscaner.databinding.FragmentHomeBinding
import com.anyarscaner.helper.SharedPref
import com.anyarscaner.model.ContentData
import com.anyarscaner.model.User

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter : ImageSliderAdapter
    private val list = ArrayList<ImageData>()
    private lateinit var dots : ArrayList<TextView>

    private lateinit var handler : Handler
    private lateinit var runnable : Runnable

    private lateinit var adapterContent : AdapterContent
    private lateinit var dataContent : ArrayList<ContentData>

    lateinit var s: SharedPref

    private lateinit var user : User


    lateinit var tv_nama: TextView
//    lateinit var tv_email: TextView
//    lateinit var tv_poin: TextView
//    lateinit var tv_jml_hadiah: TextView

    lateinit var btn_editprofil : LinearLayout
    lateinit var btn_riwayat : LinearLayout
    lateinit var btn_poinMall : LinearLayout
    lateinit var btn_about : LinearLayout
    lateinit var btn_logout : LinearLayout

    lateinit var btn_support : ImageView

//    lateinit var pbPoin : ProgressBar
//    lateinit var pbJmlRed :ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        init(binding.root)

        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable{
            var index = 0
            override fun run() {
                if (index == list.size)
                    index = 0
                Log.e("Runnable, ", "$index")
                binding.viewPager.setCurrentItem(index)
                index++
                handler.postDelayed(this,4000)
            }
        }

        s = SharedPref(requireActivity())

        setData()
        button(binding.root)
        url_img_slider()

//        SLider
        adapter = ImageSliderAdapter(list)
        binding.viewPager.adapter = adapter
        dots = ArrayList()
        setIndocator()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })

//        Content
        dataContent = ArrayList<ContentData>()
        adapterContent = AdapterContent(requireActivity(),dataContent)
        val contenRecycler = binding.rvContent
        contenRecycler.layoutManager = LinearLayoutManager(activity)
        contenRecycler.adapter = adapterContent

        dataContent()

        return binding.root
    }

    private fun dataContent() {
        dataContent.add(ContentData("DH-HAC-T1A29P & DH-HAC-B1A29P",R.drawable.content2,"DH-HAC-T1A29P\n" +
                "2MP General HDCVI White Light Eyeball Camera\n" +
                "\n" +
                "EAN:\n" +
                "· Full-color starlight\n" +
                "· 20 m illumination distance\n" +
                "· Max. 30fps@1080P\n" +
                "· CVI/CVBS/AHD/TVI switchable\n" +
                "· Fixed lens (2.8 mm;3.6 mm optional)\n" +
                "· IP67, 12V±30% DC\n" +
                "\n" +
                "DH-HAC-B1A29P\n" +
                "2MP General HDCVI White Light IR-Bullet Box Camera\n" +
                "\n" +
                "EAN:\n" +
                "· Full-color starlight\n" +
                "· 20 m illumination distance\n" +
                "· Max. 30fps@1080P\n" +
                "· CVI/CVBS/AHD/TVI switchable\n" +
                "· Fixed lens (2.8 mm;3.6 mm optional)\n" +
                "· IP67, 12V±30% DC\n" +
                "Garansi 2 Tahun Resmi Dahua."))
        dataContent.add(ContentData("NVR Uniarch 8 Channel 2MP NVR-108B",R.drawable.content1,"NVR Uniarch 8 Channel 2MP NVR-108B Metal case\n" +
                "\n" +
                "Key Features\n" +
                ". Support Ultra 265/H.265/H.264 video formats\n" +
                ". 4/8-channel input\n" +
                ". Third-party IP cameras supported with ONVIF conformance: Profile S, Profile G, Profile T\n" +
                ". Support 1-ch HDMI, 1-ch VGA\n" +
                ". HDMI and VGA simultaneous output\n" +
                ". Up to 2MP resolution recording\n" +
                ". 1 SATA HDD up to 10 TB\n" +
                ". Support cloud upgrade\n"))
        dataContent.add(ContentData("IMOU IPC-S42FP / IMOU CRUISER 4MP",R.drawable.content3,"1/2.7” 4 Megapixel Progressive CMOS\n" +
                "4MP(2650 x 1440)\n" +
                "Night Vision: 30m(98ft) Distance\n" +
                "3.6mm/6mm Fixed Lens\n" +
                "Field of View :\n" +
                "3.6mm: 3.6mm: 88°(H), 46°(V), 107°(D)\n" +
                "6mm: 54°(H), 30°(V), 64°(D)\n" +
                "1 x 100Mbps Ethernet Port\n" +
                "Wi-Fi: IEEE802.11b/g/n, Dual Antenna\n" +
                "Imou App: iOS, Android\n" +
                "Onvif\n" +
                "Micro SD Card Slot (up to 256GB)\n" +
                "Reset Button\n"+"Video Compression : H.265/H.264\n" +
                "Up to 25/30fps Frame Rate\n" +
                "16x Digital Zoom\n" +
                "Built-in Mic & Speaker, 110dB Siren\n" +
                "Two-way Audio\n"+"DC 12V1A Power Supply\n" +
                "Power Consumption: ＜ 5.32W\n" +
                "Material: Plastic\n" +
                "Working Environment:-30°C~+60°C, Less Than 95%RH\n" +
                "Dimensions: 138.4 × 121.7× 257.5mm (5.45× 4.79 × 10.14 inch)\n" +
                "Weight: 565g (1.25lb)\n"))
    }

    private fun url_img_slider() {
        list.add(
            ImageData(
                "https://gpt.poin.cctvbandung.co.id/public/storage/Hadiah/FotoHadiah/202227051553132_slider3.jpg"
            )
        )

        list.add(
            ImageData(
                "https://gpt.poin.cctvbandung.co.id/public/storage/Hadiah/FotoHadiah/20222705141674_slider2.jpg"
            )
        )

        list.add(
            ImageData(
                "https://gpt.poin.cctvbandung.co.id/public/storage/Hadiah/FotoHadiah/202227051502617_slider4.jpg"
            )
        )
    }

    private fun selectedDot(position: Int) {
        for (i in 0 until list.size){
            if(i == position){
                dots[i].setTextColor(ContextCompat.getColor(requireActivity(), R.color.biru2))
            }else{
                dots[i].setTextColor(ContextCompat.getColor(requireActivity(), R.color.abu_muda))
            }
        }
    }

    private fun setIndocator() {
        for (i in 0 until list.size){
            dots.add(TextView(activity))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].text = Html.fromHtml("&#9679",Html.FROM_HTML_MODE_LEGACY).toString()
            }else{
                dots[i].text = Html.fromHtml("&#9679")
            }
            dots[i].textSize = 18f
            binding.dotsIndicator.addView(dots[i])
        }
    }

    private fun button(view: View) {
        btn_editprofil = view.findViewById(R.id.lv_profil)
        btn_riwayat = view.findViewById(R.id.btn_riwayat)
        btn_poinMall = view.findViewById(R.id.btn_poin_mall)
        btn_about = view.findViewById(R.id.btn_about_us)
        btn_logout = view.findViewById(R.id.btn_logout)
        btn_support = view.findViewById(R.id.btn_support)

        btn_riwayat.setOnClickListener {
            if (s.getStatusLogin()){
                val inData = Intent(activity, RiwayatActivity::class.java)
                startActivity(inData)
            } else {
                val inData = Intent(activity, LoginActivity::class.java)
                startActivity(inData)
                inData.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }

        }

        btn_poinMall.setOnClickListener {
            if (s.getStatusLogin()){
                val inData = Intent(activity, PoinMallActivity::class.java)
                startActivity(inData)
            } else {
                val inData = Intent(activity, LoginActivity::class.java)
                startActivity(inData)
                inData.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }

        btn_editprofil.setOnClickListener {
            if (s.getStatusLogin()){
                val inData =Intent(activity, EditProfilActivity::class.java)
                startActivity(inData)
            } else {
                val inData = Intent(activity, LoginActivity::class.java)
                startActivity(inData)
                inData.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }

        btn_about.setOnClickListener {
            val inData =Intent(activity, AboutActivity::class.java)
            startActivity(inData)
        }

        btn_logout.setOnClickListener{
            s.setStatusLogin(false)
            val inData = Intent(activity, LoginActivity::class.java)
            inData.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            inData.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            Toast.makeText(requireActivity(),"Anda Logout dari akun anda",Toast.LENGTH_SHORT).show();
            startActivity(inData)
        }

        btn_support.setOnClickListener {
            val inData =Intent(activity, SupportActivity::class.java)
            startActivity(inData)
        }
//
//        tv_poin.setOnClickListener {
//            val inData = Intent(activity, RiwayatActivity::class.java)
//            startActivity(inData)
//        }
//
//        tv_jml_hadiah.setOnClickListener {
//            val inData = Intent(activity, RiwayatRedeemActivity::class.java)
//            startActivity(inData)
//        }

    }

    private fun init(view: View) {
        tv_nama = view.findViewById(R.id.tv_nama_user)
//        tv_email = view.findViewById(R.id.txt_email)
//        tv_poin = view.findViewById(R.id.tv_totalPoin)
//        tv_jml_hadiah = view.findViewById(R.id.tv_jml_redeem)
//        pbPoin = view.findViewById(R.id.pb_totalPoin)
//        pbJmlRed = view.findViewById(R.id.pb_jmlRedeem)
    }

    fun setData(){
        if (s.getStatusLogin()){
            binding.tvLoginlogout.setText("Logout")
        }else{
            binding.tvLoginlogout.setText("Login")
        }

//        if (s.getUser() == null){
//            val intent = Intent(activity, LoginActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(intent)
//            return
//        }

//        user = s.getUser()!!


        tv_nama.text = s.getString(s.name)
//        tv_email.text = user.email

//        pbPoin.visibility = View.VISIBLE

//        ApiConfig.instanceRetrofit.totalPoin(tv_nama.text.toString()).enqueue(object : Callback<ResponModel>{
//            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
//                val respon = response.body()!!
//                s.setString(s.total_poin, respon.total_poin)
//
//                pbPoin.visibility = View.GONE
//
//                if (respon.success == 1) {
//                    val getPoin = s.getString(s.total_poin)
//                    tv_poin.text = getPoin
//                }
//            }
//
//            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
//                Toast.makeText(requireActivity(), "Terjadi Kesalahan: "+t.message, Toast.LENGTH_SHORT).show()
//            }
//
//        }).toString()

//        ApiConfig.instanceRetrofit.totalPoin(user.name).enqueue(object : Callback<ResponModel>{
//            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
//                val respon = response.body()!!
//                s.setString(s.total_poin, respon.total_poin)
//
//                pbPoin.visibility = View.GONE
//
//                if (respon.success == 1) {
//                    val getPoin = s.getString(s.total_poin)
//                    tv_poin.text = getPoin
//                }
//            }
//
//            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
//                Toast.makeText(requireActivity(), "Terjadi Kesalahan: "+t.message, Toast.LENGTH_SHORT).show()
//            }
//
//        }).toString()
        

//        pbJmlRed.visibility = View.VISIBLE
//        tv_jml_hadiah.text = ApiConfig.instanceRetrofit.jumlah(tv_nama.text.toString()).enqueue(object : Callback<ResponModel> {
//            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
//                val respon = response.body()!!
//                s.setString(s.jumlah, respon.jumlah).toString()
//
//                pbJmlRed.visibility = View.GONE
//                if(respon.success == 1){
//                    val getJumlah = s.getString(s.jumlah)
//
//                    tv_jml_hadiah.text = getJumlah
//                }else{
//                    Toast.makeText(activity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
//                Toast.makeText(activity, "Error: "+t.message, Toast.LENGTH_SHORT).show()
//            }
//        }).toString()
    }

    override fun onStart() {
        super.onStart()
        handler.post(runnable)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }
}
