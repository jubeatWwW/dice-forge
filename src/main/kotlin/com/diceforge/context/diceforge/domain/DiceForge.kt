package com.diceforge.context.diceforge.domain

import com.diceforge.context.player.domain.PlayerId
import com.diceforge.context.shared.domain.BaseAggregateRoot

const val MAX_PLAYER = 4
const val MIN_PLAYER = 2

class DiceForge(override var id: DiceForgeId?, private val players: List<PlayerId>, round: Int) :
  BaseAggregateRoot<DiceForge, DiceForgeId>() {
  constructor(id: DiceForgeId?, players: List<PlayerId>) : this(id, players, 1)

  private val goldSetting = listOf(3, 2, 1, 0)
  private val maxRound: Int
  var round: Int
    private set

  init {
    if (players.size < MIN_PLAYER || players.size > MAX_PLAYER) {
      throw IllegalArgumentException("The number of players must be between 2 and 4")
    }

    maxRound = if (players.size == 3) {
      9
    } else {
      10
    }

    this.round = round

    if (this.round < 1 || this.round > maxRound) {
      throw IllegalArgumentException("The round must be greater than 0 and smaller than 11")
    }
  }

  fun nextRound() {
    round++
  }

  fun isLastRound() = round == maxRound

  fun getInitialGoldInventoryByPlayerOrder(playerId: PlayerId): GoldInventory {
    val index = players.indexOf(playerId)
    if (index < 0) {
      throw IllegalArgumentException("The player must be part of the game")
    }
    return GoldInventory(goldSetting[index])
  }

  fun getNumberOfCardsPerStack(): Int {
    return players.size
  }

  fun getDieFacesPerPool(): Int {
    return if (players.size == 2) {
      2
    } else {
      4
    }
  }
}
