package com.diceforge.context.diceforge.application

import com.diceforge.common.cqrs.CommandHandler
import com.diceforge.context.diceforge.domain.DiceForge
import com.diceforge.context.player.domain.Player

class InitializeDiceForgeCommandHandler : CommandHandler<InitializeDiceForgeCommand, Unit> {
  override fun handle(command: InitializeDiceForgeCommand) {
    val game = DiceForge(null, command.playerIds)
    command.playerIds.map {
      Player(it).addInventory(game.getInitialGoldInventoryByPlayerOrder(it))
    }
  }
}
