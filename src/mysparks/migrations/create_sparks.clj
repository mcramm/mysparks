(ns mysparks.migrations.create-sparks
  (:require [clojure.java.jdbc :as sql]))

(defn up []
  (sql/with-connection (System/getenv "DATABASE_URL")
                       (sql/create-table :sparks
                                         [:id :serial "PRIMARY KEY"]
                                         [:content :varchar "NOT NULL"]
                                         [:created :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"])))
(defn down []
  println "Does nothing...")

(defn -main []
  (println "Creating sparks table...") (flush)
  (up)
  (println "Done"))
