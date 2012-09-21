(ns mysparks.migrations.add-parent-id-to-sparks
  (:require [clojure.java.jdbc :as sql]))

(defn up []
  (sql/with-connection (System/getenv "DATABASE_URL")
                       (sql/do-commands "ALTER TABLE sparks ADD COLUMN parent_id integer DEFAULT NULL")))
(defn down []
  println "Does nothing...")

(defn -main []
  (println "Updating sparks table...") (flush)
  (up)
  (println "Done"))
