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

(defpartial spark-common [spark]
            [:div.content (:content spark)]
            [:div.created (:created spark)])

(defpartial condensed-spark-block [spark]
  [:div.spark
   (spark-common spark)
   [:div.num-additions (count (:additions spark))]])

(defpartial addition-block [addition]
            [:div.addition (spark-common addition)])

(defpartial full-spark-block [spark]
            [:div.spark
             (spark-common spark)
             [:div.num-additions (count (:additions spark))]
             [:div.additions
              (map addition-block (:additions spark))]])

(defpartial root [sparks]
            (base [:div.controls
                   [:a#add-spark "Add Spark"]
                   [:a#read-sparks {:href "/read" :target "_blank"} "Read Sparks"]]
                  [:div.sparks
                   (map condensed-spark-block sparks)]))

(defpartial read-sparks [sparks]
            (base [:div.sparks
                   (map full-spark-block sparks)]))

