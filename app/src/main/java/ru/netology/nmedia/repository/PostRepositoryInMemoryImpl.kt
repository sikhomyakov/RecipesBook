package ru.netology.nmedia.repository

import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.*

class PostRepositoryInMemoryImpl : PostRepository {

    private val posts
        get() = checkNotNull(data.value) {

        }

    override val data = MutableLiveData(
        List(100) { index ->
            Post(
                id = index + 1L,
                author = "Нетология. Университет интернет-профессий будущего ",
                content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
                published = "21 мая в 18:36",
                likedByMe = false,
                likes = 999,
                shares = 993,
                sharedByMe = false,
                views = 5,
                viewedBy = false,
            )
        }
    )

    override fun like(id: Long) {
        data.value = posts.map {
            if (it.id != id) it
            else
                it.copy(
                    likedByMe = !it.likedByMe,
                    likes = if (it.likedByMe) it.likes - 1 else it.likes + 1
                )
        }
    }

    override fun share(id: Long) {
        data.value = posts.map {
            if (it.id != id) {
                it
            } else {
                it.copy(shares = it.shares + 1)
            }
        }
    }

    override fun delete(id: Long) {
        data.value = posts.filter { it.id != id }
    }

    override fun view(id: Long) {
        data.value = posts.map {
            if (it.id != id) {
                it
            } else {
                it.copy(views = it.views + 1)
            }
        }
    }

    override fun edit(id: Long) {
        TODO("Not yet implemented")
    }


}