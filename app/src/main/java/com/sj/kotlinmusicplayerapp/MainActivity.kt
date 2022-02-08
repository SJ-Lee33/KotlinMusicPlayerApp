package com.sj.kotlinmusicplayerapp

import android.Manifest
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
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

		// 어댑더 탱성, 어댑터에 정의한 musicList에 음원 데이터 넘김
		val adapter = MusicRecyclerAdapter()
		adapter.musicList.addAll(getMusicList())

		// 데이터가 담긴 어댑터를 리사이클러뷰에 연결
		binding.recyclerView.adapter = adapter

		// 레이아웃 매니저 실행
		binding.recyclerView.layoutManager = LinearLayoutManager(this)
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

	// 음원을 읽어오는 메서드
	fun getMusicList(): List<Music> {
		val listUrl = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI // 음원 정보의 주소
		val proj = arrayOf(
			// 칼럼명 .. MediaStore에 이미 상수로 정의돼있음
			MediaStore.Audio.Media._ID,
			MediaStore.Audio.Media.TITLE,
			MediaStore.Audio.Media.ARTIST,
			MediaStore.Audio.Media.ALBUM_ID,
			MediaStore.Audio.Media.DURATION
		)

		// 콘텐트리졸버 실행결과를 커서로 반환
		val cursor = contentResolver.query(listUrl, proj, null, null, null)

		// 커서로 전달받은 데이터를 꺼내서 저장할 목록 변수
		val musicList = mutableListOf<Music>()

		// 커서를 이동하며 데이터를 한줄씩 읽음, 읽은 데이터를 Music클래스를 통해서 musicList에 하나씩 담음
		while (cursor?.moveToNext() == true) {
			val id = cursor.getString(0)
			val title = cursor.getString(1)
			val artist = cursor.getString(2)
			val albumId = cursor.getString(3)
			val duration = cursor.getLong(4)

			val music = Music(id, title, artist, albumId, duration)
			musicList.add(music)
		}

		// 음원이 전부 담긴 musicList를 호출한 측에 반환
		return musicList
	}
}