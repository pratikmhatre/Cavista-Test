package com.example.cavista_test.data.network.responses

class ImagesResponse {
    var status = 0
    var succes = false
    var data: List<Data>? = null

    class Data {
        var id: String? = null
        var title: String? = null
        var images: List<Image>? = null

        class Image {
            var id: String? = null
            var link: String? = null
            var title: String? = null
        }
    }
}