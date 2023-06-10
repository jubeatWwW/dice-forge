package com.diceforge.context.diceforge.application

import com.diceforge.common.cqrs.Command
import com.diceforge.context.player.domain.PlayerId

data class InitializeDiceForgeCommand(val playerIds: List<PlayerId>) : Command<Unit>
