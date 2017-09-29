package katas.gildedrose

class GildedRose(internal var items: Array<Item>) {

    val CONCERT_TICKETS = "Backstage passes to a TAFKAL80ETC concert"
    val AGED_BRIE = "Aged Brie"
    val SULFURAS = "Sulfuras, Hand of Ragnaros"

    fun updateQuality() {
        for (i in items.indices) {
            val item = items[i]
            if (item.name == SULFURAS) {
                continue
            }

            if (item.name == AGED_BRIE) {
                increaseQuality(item)
                decreaseSellIn(item)
                if (itemExpired(item)) {
                    increaseQuality(item)
                }
            } else if (item.name == CONCERT_TICKETS) {
                if (item.sellIn > 10) {
                    increaseQuality(item)
                } else if (item.sellIn in 6..10) {
                    increaseQuality(item, 2)
                } else if (item.sellIn < 6) {
                    increaseQuality(item, 3)
                }
                decreaseSellIn(item)
                if (itemExpired(item)) {
                    item.quality = 0
                }
            } else {
                decreaseQuality(item)
                decreaseSellIn(item)
                if (itemExpired(item)) {
                    decreaseQuality(item)
                }
            }
        }
    }

    private fun decreaseSellIn(item: Item) {
        item.sellIn = item.sellIn - 1
    }
}

private fun itemExpired(item: Item) = item.sellIn < 0

private fun decreaseQuality(item: Item) {
    item.quality = (item.quality - 1).coerceAtLeast(0)
}

private fun increaseQuality(item: Item, amount: Int = 1) {
    item.quality = (item.quality + amount).coerceAtMost(50)
}
