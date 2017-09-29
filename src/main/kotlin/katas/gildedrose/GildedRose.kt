package katas.gildedrose

class GildedRose(internal var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            val item = items[i]
            if (item.name == "Sulfuras, Hand of Ragnaros") {
                break
            }

            if (item.name == "Aged Brie" || item.name == "Backstage passes to a TAFKAL80ETC concert") {
                if (item.quality < 50) {
                    increaseQuality(item)

                    if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                increaseQuality(item)
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                increaseQuality(item)
                            }
                        }
                    }
                }
            } else {
                if (item.quality > 0) {
                    decreaseQuality(item)
                }
            }


            item.sellIn = item.sellIn - 1


            if (item.sellIn < 0) {
                if (item.name == "Aged Brie") {
                    if (item.quality < 50) {
                        increaseQuality(item)
                    }
                } else {
                    if (item.name != "Backstage passes to a TAFKAL80ETC concert") {
                        if (item.quality > 0) {
                            if (item.name != "Sulfuras, Hand of Ragnaros") {
                                decreaseQuality(item)
                            }
                        }
                    } else {
                        item.quality = item.quality - item.quality
                    }
                }
            }
        }
    }

    private fun decreaseQuality(item: Item) {
        item.quality = item.quality - 1
    }

    private fun increaseQuality(item: Item) {
        item.quality = item.quality + 1
    }
}
