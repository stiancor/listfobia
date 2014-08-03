(ns webdev.item.model
	(:require [clojure.java.jdbc :as db]))

(defn create-table [db]
	(db/execute! 
	db
	["CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\""])
	(db/execute!
	db
	["CREATE TABLE IF NOT EXISTS items
		(id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
		name TEXT NOT NULL,
		description TEXT NOT NULL,
		checked BOOLEAN NOT NULL DEFAULT FALSE,
		date_created TIMESTAMPTZ NOT NULL DEFAULT now())"]))
