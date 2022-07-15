package come.geekbrains.vitekm.m_2090_3.view.recycler.model


const val TYPE_EARTH = 0
const val TYPE_MARS = 1
const val TYPE_HEADER = 2

data class Data(
    val someText: String = "Text",
    val someDescription: String? = "Description",
    val type: Int = TYPE_MARS
)

