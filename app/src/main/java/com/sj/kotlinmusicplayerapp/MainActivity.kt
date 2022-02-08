package com.sj.kotlinmusicplayerapp

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import com.sj.kotlinmusicplayerapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

	val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		requirePermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 999) // 외부저장소 권한 요청
	}

	fun startProcess() {
		// 음원목록을 불러오는 코드
	}

	override fun permissionGranted(requestCode: Int) {
		startProcess()
	}

	override fun permissionDenied(requestCode: Int) {
		// 권한 승인이 필요하다는 메세지 띄우고
		Toast.makeText(this, "외부 저장소 권한 승인이 필요합니다. 앱을 종료합니다.", Toast.LENGTH_LONG).show()

		// 앱 종료
		finish()
	}
}