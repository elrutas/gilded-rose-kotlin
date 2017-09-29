package katas.gildedrose

class GildedRose(internal var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            val item = items[i]
            if (item.name == "Sulfuras, Hand of Ragnaros") {
                continue
            }

            updateQuality(item)
            updateSellIn(item)
        }
    }

    private fun updateQuality(item: Item) {
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
    }

    private fun updateSellIn(item: Item) {
        item.sellIn = item.sellIn - 1

        if (itemExpired(item)) {
            if (item.name == "Aged Brie") {
                if (item.quality < 50) {
                    increaseQuality(item)
                }
            } else if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                item.quality = 0
            } else {
                if (item.quality > 0) {
                    decreaseQuality(item)
                }
            }
        }
    }
}

private fun itemExpired(item: Item) = item.sellIn < 0

private fun decreaseQuality(item: Item) {
    item.quality = item.quality - 1
}

private fun increaseQuality(item: Item) {
    item.quality = item.quality + 1
}
