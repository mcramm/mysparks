(ns mysparks.views.templates
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css html5]]))

(defpartial layout [& content]
            (html5
              [:head
               [:title "My Sparks"]
               (include-css "/css/reset.css")]
              [:body
               [:div.wrapper
                content]]))

(defpartial base [& content]
            (layout [:div.header
                     [:h1 "My Sparks"]] content))

(defpartial root []
            (base [:div.controls
                   [:a#add-spark "Add Spark"]
                   [:a#read-sparks {:href "/read" :target "_blank"} "Read Sparks"]]))

(defpartial read-sparks []
            (base [:p "This is where you would read a spark"]))

