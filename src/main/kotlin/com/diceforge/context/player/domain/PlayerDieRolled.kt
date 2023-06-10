package com.diceforge.context.player.domain

import com.diceforge.context.shared.domain.DomainEvent
import com.diceforge.context.shared.domain.time.Timestamp

data class PlayerDieRolled(val player: Player, val index: Int, override val occurredAt: Timestamp) :
  DomainEvent
