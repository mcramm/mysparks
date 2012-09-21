(ns mysparks.views.core
  (:require [mysparks.templates :as templates]
            [noir.response :as response]
            [mysparks.spark :as spark])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/" []
         (templates/root (spark/all)))

(defpage "/read" []
         (templates/read-sparks (spark/all-reverse)))

(defpage [:post "/sparks"] {content :content}
         (spark/create content)
         (response/redirect "/"))

(defpage [:post "/sparks/:spark-id/additions"] {content :content
                                                spark-id :spark-id}
         (spark/add-addition spark-id content)
         (response/redirect "/"))
