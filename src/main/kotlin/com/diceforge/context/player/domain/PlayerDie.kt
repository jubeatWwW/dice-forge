package com.diceforge.context.player.domain

import com.diceforge.context.shared.domain.BaseEntity
import com.diceforge.context.diceforge.domain.DiceFaceId

class PlayerDie(
  override var id: PlayerDieId?,
  private val faces: MutableList<DiceFaceId>,
  private var currentFace: DiceFaceId
) :
  BaseEntity<PlayerDie, PlayerDieId>() {
  init {
    if (faces.size != 6) {
      throw IllegalArgumentException("A die must have 6 faces")
    }

    if (!faces.contains(currentFace)) {
      throw IllegalArgumentException("The current face must be one of the faces of the die")
    }
  }

  constructor(id: PlayerDieId?, faces: MutableList<DiceFaceId>) : this(id, faces, faces[0])
  constructor(id: PlayerDieId?) : this(id, mutableListOf())
  constructor(): this(null)

  fun roll() {
    currentFace = faces.random()
  }

  fun forge(face: DiceFaceId, position: Int) {
    if (position < 0 || position > 5) {
      throw IllegalArgumentException("The position must be between 0 and 5")
    }
    faces[position] = face
  }

  fun getCurrentFace() = currentFace
}
