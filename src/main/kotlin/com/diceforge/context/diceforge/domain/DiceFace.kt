package com.diceforge.context.diceforge.domain

import com.diceforge.context.shared.domain.BaseEntity

class DiceFace(override var id: DiceFaceId?, val inventory: Inventory) : BaseEntity<DiceFace, DiceFaceId>()
