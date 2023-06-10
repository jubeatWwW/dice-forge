package com.diceforge.context.player.domain

import com.diceforge.context.diceforge.domain.GoldInventory
import com.diceforge.context.diceforge.domain.Inventory
import com.diceforge.context.diceforge.domain.MoonShardInventory
import com.diceforge.context.diceforge.domain.SunShardInventory
import com.diceforge.context.shared.domain.BaseAggregateRoot

class Player(override var id: PlayerId?, private val dice: Pair<PlayerDie, PlayerDie>) :
  BaseAggregateRoot<Player, PlayerId>() {
  constructor(id: PlayerId?) : this(id, Pair(PlayerDie(), PlayerDie()))
  constructor() : this(null)

  private val reserves = mapOf(
    GoldInventory::class.java to Reserve(null, GoldInventory(0), 12),
    MoonShardInventory::class.java to Reserve(null, MoonShardInventory(0), 6),
    SunShardInventory::class.java to Reserve(null, SunShardInventory(0), 6)
  )

  fun rollDice(full: Boolean = true) {
    dice.first.roll()
    if (full) {
      dice.second.roll()
    }
  }

  fun addInventory(inventory: Inventory) {
    val reserve = reserves[inventory::class.java] ?: throw IllegalArgumentException("The inventory is not supported")
    (reserve as Reserve<*>).add(inventory)
  }
}
