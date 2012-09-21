(ns mysparks.views.welcome
  (:require [mysparks.templates :as templates]
            [noir.response :as response]
            [mysparks.spark :as spark])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/" []
         (templates/root (spark/all)))

(defpage "/read" []
         (templates/read-sparks (spark/all)))

(defpage [:post "/sparks"] {content :content}
         (spark/create content)
         (response/redirect "/"))
