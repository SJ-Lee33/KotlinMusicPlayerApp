package com.sj.kotlinmusicplayerapp

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sj.kotlinmusicplayerapp.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

// Adapter 클래스를 상속받고, 제네릭으로 Holder를 지정함
class MusicRecyclerAdapter : RecyclerView.Adapter<Holder>() {
	// 음악 목록 저장, 제네릭으로 Music을 사용하는 컬렉션
	var musicList = mutableListOf<Music>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
		// 화면에 보이는 아이템 레이아웃의 바인딩 생성
		val binding =
			ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return Holder(binding)
	}

	override fun onBindViewHolder(holder: Holder, position: Int) {
		// 아이템 레이아웃에 데이터를 출력
		val music = musicList.get(position)
		holder.setMusic(music)

	}

	override fun getItemCount(): Int {
		// 목록의 갯수 리턴
		return musicList.size
	}
}

// 항상 바인딩 1개를 파라미터로 가지고, 상속받는 ViewHolder에 binding.root를 넘겨주는 구조
class Holder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
	var musicUri: Uri? = null

	// setMusic() 메서드의 파라미터로 넘어온 music은 메서드가 실행되는 순간만 사용 가능
	fun setMusic(music: Music) {
		// run 함수를 사용하면 매번 binding.을 입력하지 않아도 됨
		binding.run {
			imageAlbum.setImageURI(music.getAlbumUri())
			textArtist.text = music.artist
			textTitle.text = music.title

			val duration = SimpleDateFormat("mm:ss").format(music.duration)
			textDuration.text = duration
		}
		this.musicUri = music.getMusicUri()
	}
}