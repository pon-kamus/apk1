package com.example.hiraganapractice

data class Kana(
    val char: String,
    val romaji: String,
    val instruction: String = ""
)

object KanaData {
    val romajiMap = mapOf(
        "あ" to "a", "い" to "i", "う" to "u", "え" to "e", "お" to "o",
        "か" to "ka", "き" to "ki", "く" to "ku", "け" to "ke", "こ" to "ko",
        "さ" to "sa", "し" to "shi", "す" to "su", "せ" to "se", "そ" to "so",
        "た" to "ta", "ち" to "chi", "つ" to "tsu", "て" to "te", "と" to "to",
        "な" to "na", "に" to "ni", "ぬ" to "nu", "ね" to "ne", "の" to "no",
        "は" to "ha", "ひ" to "hi", "ふ" to "fu", "へ" to "he", "ほ" to "ho",
        "ま" to "ma", "み" to "mi", "む" to "mu", "め" to "me", "も" to "mo",
        "や" to "ya", "ゆ" to "yu", "よ" to "yo",
        "ら" to "ra", "り" to "ri", "る" to "ru", "れ" to "re", "ろ" to "ro",
        "わ" to "wa", "を" to "wo", "ん" to "n"
    )

    val kanaInstructions = mapOf(
        "あ" to "3 Coretan: 1. Kiri-kanan, 2. Atas-bawah melengkung, 3. Melingkar dari tengah.",
        "い" to "2 Coretan: 1. Kiri atas-bawah (dgn kait), 2. Kanan atas-bawah (pendek).",
        "う" to "2 Coretan: 1. Titik/garis atas, 2. Lengkungan lebar dari atas ke bawah.",
        "え" to "2 Coretan: 1. Titik/garis atas, 2. Bentuk \"Z\" melengkung dgn ekor mendatar.",
        "お" to "3 Coretan: 1. Kiri-kanan, 2. Vertikal menembus & melingkar, 3. Titik di kanan atas.",
        "か" to "3 Coretan: 1. Horizontal-melengkung turun (kait), 2. Vertikal memotong, 3. Garis pendek kanan.",
        "き" to "4 Coretan: 1 & 2. Dua garis horizontal, 3. Vertikal memotong miring, 4. Lengkungan bawah.",
        "く" to "1 Coretan: Garis siku tunggal dari kanan atas ke kiri lalu turun ke kanan (spt \"<\").",
        "け" to "3 Coretan: 1. Vertikal kiri (kait), 2. Horizontal pendek, 3. Vertikal panjang melengkung tembus.",
        "こ" to "2 Coretan: 1. Horizontal atas (kait melengkung), 2. Horizontal bawah (lengkung ke atas).",
        "さ" to "3 Coretan: 1. Horizontal memanjang, 2. Vertikal miring memotong, 3. Lengkungan bawah terpisah.",
        "し" to "1 Coretan: Vertikal lurus ke bawah lalu melengkung lembut ke kanan atas (seperti kail).",
        "す" to "2 Coretan: 1. Horizontal, 2. Vertikal memotong lurus turun, membuat loop/bulatan tengah lalu turun.",
        "せ" to "3 Coretan: 1. Horizontal, 2. Vertikal kanan (pendek), 3. Vertikal kiri lalu belok siku ke kanan.",
        "そ" to "1 Coretan: Horizontal ke kiri, zigzag ke kanan, lalu melingkar turun ke bawah (seperti \"Z\" & \"C\").",
        "た" to "4 Coretan: 1. Horizontal kiri, 2. Vertikal memotong miring, 3 & 4. Seperti huruf \"こ\" di sampingnya.",
        "ち" to "2 Coretan: 1. Horizontal, 2. Vertikal memotong turun lalu melengkung membentuk perut membulat.",
        "つ" to "1 Coretan: Melengkung lebar seperti tapal kuda dari kiri atas ke kanan lalu turun meruncing ke kiri bawah.",
        "て" to "1 Coretan: Garis horizontal menyamping lalu seketika melengkung melingkar turun ke kiri bawah.",
        "と" to "2 Coretan: 1. Garis serong pendek atas, 2. Melengkung dari atas menyelimuti titik pertama (spt \"C\").",
        "な" to "4 Coretan: 1. Horizontal pendek, 2. Vertikal memotong, 3. Titik terbang di kanan, 4. Lengkungan melingkar/loop turun.",
        "に" to "3 Coretan: 1. Vertikal kiri (dengan kait), 2 & 3. Seperti karakter \"こ\" bersusun di sisi kanan.",
        "ぬ" to "2 Coretan: 1. Garis diagonal kiri turun, 2. Diagonal melengkung bersilangan memutar dengan loop di ekornya.",
        "ね" to "2 Coretan: 1. Vertikal lurus tegak tebal, 2. Garis \"Z\" diagonal turun, memanjat naik dan berputar membuat loop akhir.",
        "の" to "1 Coretan: Mulai di tengah turun menyerong, lalu melengkung putar ke atas, kanan and turun ke bawah menyelimuti.",
        "は" to "3 Coretan: 1. Vertikal kiri (kait), 2. Horizontal kanan memanjang, 3. Vertikal potong turun dengan loop melingkar bawah.",
        "ひ" to "1 Coretan: Melengkung seperti huruf \"U\" sangat lebar, berawal miring naik, landai turun dan memutar naik lagi.",
        "ふ" to "4 Coretan: 1. Titik puncak atas, 2. Lengkungan pusat menyerupai kail tegak, 3. Titik sapuan kiri, 4. Titik kanan.",
        "へ" to "1 Coretan: Garis memanjat miring ke atas secara pendek kemudian menukik turun with miring secara panjang (atap).",
        "ほ" to "4 Coretan: 1. Vertikal kiri (kait), 2 & 3. Dua garis horizontal sejajar, 4. Vertikal potong tembus turun dengan loop (jgn melewati atas).",
        "ま" to "3 Coretan: 1 & 2. Dua horizontal memanjang sejajar, 3. Vertikal sentral memotong tegak lurus ke bawah dgn loop melingkar.",
        "み" to "2 Coretan: 1. Garis menyamping miring turun belok ke kanan membentuk loop memanjang datar, 2. Vertikal memotong ke bawah.",
        "む" to "3 Coretan: 1. Horizontal mendatar, 2. Vertikal turun membuat loop kecil bergeser dengan ekor panjang menyamping, 3. Titik puncak.",
        "め" to "2 Coretan: 1. Garis diagonal sisi kiri miring memotong, 2. Melengkung silang sangat tebal melingkar (seperti \"ぬ\" tapi ujung polos / tanpa loop).",
        "も" to "3 Coretan: 1. Garis lurus ke bawah berbelok jadi kail spt payung (digambar duluan), 2 & 3. Dua sapuan horizontal tegas menyeberang vertikal.",
        "や" to "3 Coretan: 1. Lengkungan kurva mangkuk miring ke atas, 2. Titik miring memotong melayang di atas, 3. Coretan vertikal penjang memotong tepi.",
        "ゆ" to "2 Coretan: 1. Vertikal pendek lurus, belok melengkung U melebar naik melengkung masuk, 2. Tiang Vertikal panjang lurus menusuk.",
        "よ" to "2 Coretan: 1. Setrip horizontal sangat pendek bagian kanan, 2. Setrip horizontal atas bersambung vertikal memotong tajam membuat loop penuh di alas.",
        "ら" to "2 Coretan: 1. Setrip sapuan serong melayang di ujung atas, 2. Tiang panjang lurus turun secara bertahap melengkung membuang terbuka ke arah kanan.",
        "り" to "2 Coretan: 1. Pilar vertikal kiri lurus sedikit pendek menukik melempar (ada kait), 2. Pilar vertikal kanan jauh lebih panjang, mulus melengkung kiri menukik.",
        "る" to "1 Coretan: Sapuan lurus horizontal from kiri ke kanan lalu menyerong zig zag patah tajam memutar membuat perut & berhenti membulat dengan loop utuh kail bawah.",
        "れ" to "2 Coretan: 1. Setrip tegak lurus utuh lurus, 2. Mulai horizontal belok zig zag diagonal turun menukik, lalu belok melengkung membuang menendang terbuka keatas (bukan loop).",
        "ろ" to "1 Coretan: Persis karakter \"る\", mulai melintang belok patah sudut miring, lalu membuat perut bulat tanpa henti namum Ujungnya polos dibuang begitu saja tanpa loop di ujung bawah.",
        "わ" to "2 Coretan: 1. Pilar lurus tegak polos lurus mantap menancap, 2. Mulai menyeberang belok diagonal zig zag putar menukik bulat membuang besar melingkar tumpul terbuka kearah bawah (spt \"ne\").",
        "を" to "3 Coretan: 1. Setrip lurus mendatar menyamping, 2. Titik potong menyilang turun belok melengkung meruncing mengail keluar, 3. Bentuk melengkung persis seperti huruf \"C\" bertabrakan miring.",
        "ん" to "1 Coretan: Tunggal menyapu menyerong miring tebal meluncur turun sudut kiri, tiba2 memantul membalik miring meruncing kearah sudut atas kanan lalu lentur melengkung turun secara memudar tipis."
    )

    val kanaChart = listOf(
        "あ", "い", "う", "え", "お",
        "か", "き", "く", "け", "こ",
        "さ", "し", "す", "せ", "そ",
        "た", "ち", "つ", "て", "と",
        "な", "に", "ぬ", "ね", "の",
        "は", "ひ", "ふ", "へ", "ほ",
        "ま", "み", "む", "め", "も",
        "や", "", "ゆ", "", "よ",
        "ら", "り", "る", "れ", "ろ",
        "わ", "", "", "", "を",
        "ん", "", "", "", ""
    )

    val validKana: List<Kana> = kanaChart.filter { it.isNotEmpty() }.map {
        Kana(it, romajiMap[it] ?: "", kanaInstructions[it] ?: "")
    }
}
