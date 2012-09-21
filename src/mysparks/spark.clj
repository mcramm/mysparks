(ns mysparks.spark
  (:require [clojure.java.jdbc :as sql]))

(defn create [content]
  (sql/with-connection (System/getenv "DATABASE_URL")
                       (sql/insert-values :sparks [:content] [content])))

(defn get-additions-for-spark [spark-id]
  (sql/with-connection (System/getenv "DATABASE_URL")
                       (sql/with-query-results results
                                               [(str "SELECT * FROM sparks WHERE parent_id = " spark-id " ORDER BY created")]
                                               (into [] results))))

(defn add-addition [spark-id content]
  (sql/with-connection (System/getenv "DATABASE_URL")
                       (sql/insert-values :sparks [:parent_id :content]
                                                  [(Integer/parseInt spark-id) content])))
(defn append-additions [spark]
  (let [additions (get-additions-for-spark (:id spark))]
    (assoc spark :additions additions)))

(defn get-all []
  (let [results (sql/with-connection (System/getenv "DATABASE_URL")
                       (sql/with-query-results results
                                               [(str "SELECT * FROM sparks WHERE parent_id IS NULL ORDER BY created")] 
                                               (into [] results)))]
    (map append-additions (set results))))

  
(defn all []
  ; This looks inverted, but it isn't oddness with clojure's sequences I
  ; think...
  (reverse (get-all)))

(defn all-reverse []
  (get-all))

