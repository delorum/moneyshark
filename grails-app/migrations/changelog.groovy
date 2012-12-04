databaseChangeLog = {

	changeSet(author: "andrey (generated)", id: "1354627423942-1") {
		createTable(tableName: "balance") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "balancePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "balance", type: "longvarchar") {
				constraints(nullable: "false")
			}

			column(name: "pewpew1", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "comment", type: "longvarchar") {
				constraints(nullable: "false")
			}

			column(name: "pewpew2", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "andrey (generated)", id: "1354627423942-2") {
		createTable(tableName: "encrypted_data") {
			column(name: "id", type: "varchar(32)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "encrypted_datPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "data_item", type: "varchar(512)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "andrey (generated)", id: "1354627423942-3") {
		createTable(tableName: "income") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "incomePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "amount", type: "longvarchar") {
				constraints(nullable: "false")
			}

			column(name: "pewpew1", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "comment", type: "longvarchar")

			column(name: "pewpew2", type: "integer")

			column(name: "date", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "andrey (generated)", id: "1354627423942-4") {
		createTable(tableName: "outcome") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "outcomePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "amount", type: "longvarchar") {
				constraints(nullable: "false")
			}

			column(name: "pewpew1", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "comment", type: "longvarchar")

			column(name: "pewpew2", type: "integer")

			column(name: "date", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "andrey (generated)", id: "1354627423942-5") {
		createTable(tableName: "periodic_income") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "periodic_incoPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "amount", type: "longvarchar") {
				constraints(nullable: "false")
			}

			column(name: "pewpew1", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "comment", type: "longvarchar")

			column(name: "pewpew2", type: "integer")

			column(name: "last_added", type: "timestamp")

			column(name: "periodunit", type: "longvarchar") {
				constraints(nullable: "false")
			}

			column(name: "pewpew4", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "periodicity", type: "longvarchar") {
				constraints(nullable: "false")
			}

			column(name: "pewpew3", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "start_moment", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "stop_moment", type: "timestamp")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "andrey (generated)", id: "1354627423942-6") {
		createTable(tableName: "periodic_outcome") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "periodic_outcPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "amount", type: "longvarchar") {
				constraints(nullable: "false")
			}

			column(name: "pewpew1", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "comment", type: "longvarchar")

			column(name: "pewpew2", type: "integer")

			column(name: "last_added", type: "timestamp")

			column(name: "periodunit", type: "longvarchar") {
				constraints(nullable: "false")
			}

			column(name: "pewpew4", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "periodicity", type: "longvarchar") {
				constraints(nullable: "false")
			}

			column(name: "pewpew3", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "start_moment", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "stop_moment", type: "timestamp")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "andrey (generated)", id: "1354627423942-7") {
		createTable(tableName: "promo_code") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "promo_codePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "generated_by_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "promocode", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "used_for_id", type: "bigint")
		}
	}

	changeSet(author: "andrey (generated)", id: "1354627423942-8") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "invited_by_id", type: "bigint")

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "andrey (generated)", id: "1354627423942-9") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "balance", constraintName: "FKEBC86EDC9A069578", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "andrey (generated)", id: "1354627423942-10") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "income", constraintName: "FKB969A1A99A069578", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "andrey (generated)", id: "1354627423942-11") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "outcome", constraintName: "FKBE0C07529A069578", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "andrey (generated)", id: "1354627423942-12") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "periodic_income", constraintName: "FK1D8DF3CD9A069578", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "andrey (generated)", id: "1354627423942-13") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "periodic_outcome", constraintName: "FKDE71F9AE9A069578", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "andrey (generated)", id: "1354627423942-14") {
		addForeignKeyConstraint(baseColumnNames: "generated_by_id", baseTableName: "promo_code", constraintName: "FK3A17EF1DC6246EBC", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "andrey (generated)", id: "1354627423942-15") {
		addForeignKeyConstraint(baseColumnNames: "used_for_id", baseTableName: "promo_code", constraintName: "FK3A17EF1DD5143E7C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "andrey (generated)", id: "1354627423942-16") {
		addForeignKeyConstraint(baseColumnNames: "invited_by_id", baseTableName: "user", constraintName: "FK36EBCB14EA26A8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "andrey (generated)", id: "1354627423942-17") {
		createIndex(indexName: "promocode_unique_1354627423830", tableName: "promo_code", unique: "true") {
			column(name: "promocode")
		}
	}

	changeSet(author: "andrey (generated)", id: "1354627423942-18") {
		createIndex(indexName: "email_unique_1354627423838", tableName: "user", unique: "true") {
			column(name: "email")
		}
	}
}
