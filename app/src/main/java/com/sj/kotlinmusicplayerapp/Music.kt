package com.sj.kotlinmusicplayerapp

import android.net.Uri
import android.provider.MediaStore

class Music(
	id: String, title: String?, artist: String?,
	albumId: String?, duration: Long?
) {

	var id: String = ""
	var title: String?
	var artist: String?
	var albumId: String?
	var duration: Long?

	init {
		this.id = id
		this.title = title
		this.artist = artist
		this.albumId = albumId
		this.duration = duration
	}

	// 음원의 URI 생성 ... 기본 MediaStore 주소와 음원ID의 조합
	fun getMusicUri(): Uri {
		return Uri.withAppendedPath(
			MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id
		)
	}

	// 앨범아트 URI 생성
	fun getAlbumUri(): Uri {

		return Uri.parse(
			"content://media/external/audio/albumart/" + albumId
		)
	}
}