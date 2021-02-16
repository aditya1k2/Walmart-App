package com.example.walmart.model

import com.squareup.moshi.Json

data class RetroData(
    @Json(name = "time") val time: Long,
    @Json(name = "items") val items: List<Items>
)


data class CategoryListData(
    @Json(name = "category") val category: String,
    @Json(name = "nextPage") val nextPage: String,
    @Json(name = "format") val format: String,
    @Json(name = "totalPages") val totalPages: Int,
    @Json(name = "nextPageExist") val nextPageExist: Boolean,
    @Json(name = "items") val items: List<Items>
)


data class Attributes(
    @Json(name = "chokingHazardWarning") val chokingHazardWarning: Int,
    @Json(name = "fuelRestriction") val fuelRestriction: String,
    @Json(name = "ironBankCategory") val ironBankCategory: String
)


//data class GiftOptions(
//    @Json(name = )val
//)


data class ImageEntities(
    @Json(name = "thumbnailImage") val thumbnailImage: String,
    @Json(name = "mediumImage") val mediumImage: String,
    @Json(name = "largeImage") val largeImage: String,
    @Json(name = "entityType") val entityType: String
)


data class Category(
    @Json(name = "categories") val categories: List<CategoryId>
)

data class CategoryId(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "children") val children: List<CategoryId>
)

data class Items(
//    @Json(name = "itemId") val itemId : Int,
//    @Json(name = "parentItemId") val parentItemId : Int,
    @Json(name = "name") val name: String?,
//    @Json(name = "msrp") val msrp : Double,
    @Json(name = "salePrice") val salePrice: Double?,
//    @Json(name = "upc") val upc : Long,
//    @Json(name = "categoryPath") val categoryPath : String,
    @Json(name = "shortDescription") val shortDescription: String?,
//    @Json(name = "longDescription") val longDescription : String,
    @Json(name = "brandName") val brandName: String?,
    @Json(name = "thumbnailImage") val thumbnailImage: String?,
//    @Json(name = "mediumImage") val mediumImage : String,
    @Json(name = "largeImage") val largeImage: String?,
//    @Json(name = "productTrackingUrl") val productTrackingUrl : String,
//    @Json(name = "ninetySevenCentShipping") val ninetySevenCentShipping : Boolean,
//    @Json(name = "marketplace") val marketplace : Boolean,
////    @Json(name = "shipToStore") val shipToStore : Boolean,
//    @Json(name = "freeShipToStore") val freeShipToStore : Boolean,
//    @Json(name = "modelNumber") val modelNumber : String,
//    @Json(name = "sellerInfo") val sellerInfo : String,
//    @Json(name = "productUrl") val productUrl : String,
    @Json(name = "customerRating") val customerRating: Float?
//    @Json(name = "numReviews") val numReviews : Int,
//    @Json(name = "customerRatingImage") val customerRatingImage : String,
//    @Json(name = "categoryNode") val categoryNode : String,
//    @Json(name = "rhid") val rhid : Int,
//    @Json(name = "bundle") val bundle : Boolean,
//    @Json(name = "clearance") val clearance : Boolean,
//    @Json(name = "stock") val stock : String,
////    @Json(name = "attributes") val attributes : Attributes,
//    @Json(name = "addToCartUrl") val addToCartUrl : String,
//    @Json(name = "affiliateAddToCartUrl") val affiliateAddToCartUrl : String,
//    @Json(name = "freeShippingOver35Dollars") val freeShippingOver35Dollars : Boolean,
//    @Json(name = "maxItemsInOrder") val maxItemsInOrder : Int,
////    @Json(name = "giftOptions") val giftOptions : GiftOptions,
//    @Json(name = "imageEntities") val imageEntities : List<ImageEntities>,
//    @Json(name = "offerType") val offerType : String,
//    @Json(name = "isTwoDayShippingEligible") val isTwoDayShippingEligible : Boolean,
//    @Json(name = "availableOnline") val availableOnline : Boolean
)

//data class Json4Kotlin_Base (
//
//    @Json(name = "time") val time : Int,
//    @Json(name = "items") val items : List<Items>
//)

