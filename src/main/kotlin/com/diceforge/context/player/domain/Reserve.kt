package com.diceforge.context.player.domain

import com.diceforge.context.diceforge.domain.Inventory
import com.diceforge.context.shared.domain.BaseEntity

class Reserve<T : Inventory>(
  override var id: ResourceId?,
  inventory: T,
  private var upperBound: Int
) :
  BaseEntity<Reserve<T>, ResourceId>() {
  var amount: Int = inventory.amount
    private set(value) {
      if (value < 0) {
        throw IllegalArgumentException("The amount must be between 0 and $upperBound")
      }
      field = if (value > upperBound) {
        upperBound
      } else {
        value
      }
    }

  init {
    if (inventory.amount < 0 || inventory.amount > upperBound) {
      throw IllegalArgumentException("The amount must be between 0 and $upperBound")
    }
    this.amount = inventory.amount
  }

  fun add(inventory: Inventory) {
    this.amount += inventory.amount
  }

  fun remove(amount: Int) {
    this.amount -= amount
  }

  fun extend(amount: Int) {
    upperBound += amount
  }
}
