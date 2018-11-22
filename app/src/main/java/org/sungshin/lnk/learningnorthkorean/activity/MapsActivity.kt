package org.sungshin.lnk.learningnorthkorean

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import org.sungshin.lnk.learningnorthkorean.Fragment.PyongyangFragment

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap


    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: MyLocationCallBack


    private val REQUEST_ACCESS_FINE_LOCATION = 1000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // SupportMapFragment를 가져와서 지도가 준비되면 알림을 받습니다.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

//        locationInit()

        btn_maps_act_pyongyang.setOnClickListener {
            replaceFragment(PyongyangFragment())
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.map, fragment)
        transaction.commit()
    }

    /**
     * 사용 가능한 맵을 조작합니다.
     * 지도를 사용할 준비가 되면 이 콜백이 호출됩니다.
     * 여기서 마커나 선, 청취자를 추가하거나 카메라를 이동할 수 있습니다.
     * 호주 시드니 근처에 마커를 추가하고 있습니다.
     * Google Play 서비스가 기기에 설치되어 있지 않은 경우 사용자에게
     * SupportMapFragment 안에 Google Play 서비스를 설치하라는 메시지가
     * 표시됩니다. 이 메서드는 사용자가 Google Play 서비스를 설치하고 앱으로
     * 돌아온 후에만 호출(또는 실행) 됩니다.
     */


    // 위치 정보를 얻기 위한 각종 초기화
    private fun locationInit() {
        fusedLocationProviderClient = FusedLocationProviderClient(this)

        locationCallback = MyLocationCallBack()

        locationRequest = LocationRequest()

        // GPS 우선
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        // 업데이트 인터벌
        // 위치 정보가 없을 때는 업데이트 안 함
        // 상황에 따라 짧아질 수 있음, 정확하지 않음
        // 다른 앱에서 짧은 인터벌로 위치 정보를 요청하면 짧아질 수 있음
        locationRequest.interval = 10000

        // 정확함. 이것보다 짧은 업데이트는 하지 않음
        locationRequest.fastestInterval = 5000
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

//        val LatArray: Array<Double> = arrayOf(38.659583, 37.939449, 38.752304, 41.994674, 41.011690,
//                39.298341, 40.106584, 39.039084, 39.983277, 42.246541, 38.059782, 38.542528)
//        val LngArray: Array<Double> = arrayOf(128.139967, 126.589559, 125.324543, 128.077035, 126.588016,
//                125.814002, 124.488479, 125.762496, 127.611902, 130.307013,125.704046, 125.790496)
//        val TitleArray: Array<String> = arrayOf("gangwon", "kaesong", "nampo", "yangkang", "jagang",
//                "pyeongannam", "pyeonganbuk", "pyongyang", "hamgyeongnam", "hangyeongbuk",
//                "hwanghaenam", "hwanghaebuk")


        val gangwon = LatLng(38.659583, 128.139967)
        val kaesong = LatLng(37.939449, 126.589559)
        val nampo = LatLng(38.752304, 125.324543)
        val yangkang = LatLng(41.994674, 128.077035)
        val jagang = LatLng(41.011690, 126.588016)
        val pyeongannam = LatLng(39.298341, 125.814002)
        val pyeonganbuk = LatLng(40.106584, 124.488479)
        val pyongyang = LatLng(39.039084, 125.762496)
        val hamgyeongnam = LatLng(39.983277, 127.611902)
        val hamgyeongbuk = LatLng(42.246541, 130.307013)
        val hwanghaenam = LatLng(38.059782, 125.704046)
        val hwanghaebuk = LatLng(38.542528, 125.790496)


        mMap.addMarker(MarkerOptions().position(gangwon).title("강원도"))
        mMap.addMarker(MarkerOptions().position(kaesong).title("개성특급시"))
        mMap.addMarker(MarkerOptions().position(nampo).title("남포특별시"))
        mMap.addMarker(MarkerOptions().position(yangkang).title("양강도"))
        mMap.addMarker(MarkerOptions().position(jagang).title("자강도"))
        mMap.addMarker(MarkerOptions().position(pyeongannam).title("평안남도"))
        mMap.addMarker(MarkerOptions().position(pyeonganbuk).title("평안북도"))
        mMap.addMarker(MarkerOptions().position(pyongyang).title("평양직할시"))
        mMap.addMarker(MarkerOptions().position(hamgyeongnam).title("함경남도"))
        mMap.addMarker(MarkerOptions().position(hamgyeongbuk).title("함경북도"))
        mMap.addMarker(MarkerOptions().position(hwanghaenam).title("황해남도"))
        mMap.addMarker(MarkerOptions().position(hwanghaebuk).title("황해북도"))


        // 마커 클릭에 대한 이벤트 처리
        mMap.setOnMapClickListener { this }

        // 카메라를 위치로 옮기기
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pyongyang))
    }



//
//    override fun onResume() {
//        super.onResume()
//
//        // 권한 요청
//        permissionCheck(cancel = {
//            // 위치 정보가 필요한 이유 다이얼로그 표시
//            showPermissionInfoDialog()
//        }, ok = {
//            // 현재 위치를 주기적으로 요청 (권한이 필요한 부분)
//            addLocationListener()
//        })
//    }
//
//    @SuppressLint("MissingPermission")
//    private fun addLocationListener() {
//        fusedLocationProviderClient.requestLocationUpdates(locationRequest,
//                locationCallback,
//                null)
//    }

    inner class MyLocationCallBack : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
            super.onLocationResult(p0)

            val location = p0?.lastLocation

            location?.run {
                // 14 level로 확대하고 현재 위치로 카메라 이동
                val latLng = LatLng(latitude, longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f))

                Log.d("MapsActvity", "위도 : $latitude, 경도 : $longitude")
            }
        }
    }
//
//    private fun permissionCheck(cancel: () -> Unit, ok: () -> Unit) {
//        // 위치 권한이 있는지 검사
//        if (ContextCompat.checkSelfPermission(this,
//                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // 권한이 허용되지 않음
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
//                // 이전에 권한을 한 번 거부한 적이 있는 경우에 실행할 함수
//                cancel()
//            } else {
//                // 권한 요청
//                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_ACCESS_FINE_LOCATION)
//            }
//        } else {
//            // 권한을 수락했을 때 실행할 함수
//            ok()
//        }
//    }
//
//    private fun showPermissionInfoDialog() {
//        alert("현재 위치 정보를 얻으려면 위치 권한이 필요합니다", "권한이 필요한 이유") {
//            yesButton {
//                // 권한 요청
//                ActivityCompat.requestPermissions(this@MapsActivity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
//                        REQUEST_ACCESS_FINE_LOCATION)
//            }
//            noButton { }
//        }.show()
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            REQUEST_ACCESS_FINE_LOCATION -> {
//                if ((grantResults.isNotEmpty()
//                                && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                    // 권한 혀용됨
//                    addLocationListener()
//                } else {
//                    // 권한 거부
//                    toast("권한 거부 됨")
//                }
//                return
//            }
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//
//        removeLocationListener()
//    }
//
//    private fun removeLocationListener() {
//        // 현재 위치 요청을 삭제
//        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
//    }

}
