package com.example.dto

import com.example.table.user.AuthTokenTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AuthTokenDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<AuthTokenDTO>(AuthTokenTable)
	var tokenId by AuthTokenTable.tokenId
	var accessToken by AuthTokenTable.accessToken
	var refreshToken by AuthTokenTable.refreshToken
	var issuedAt by AuthTokenTable.issuedAt
	var expiredAt by AuthTokenTable.expiredAt
	var userId by AuthTokenTable.userId
	var token by AuthTokenTable.token
	var isRevoked by AuthTokenTable.isRevoked
	
	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
